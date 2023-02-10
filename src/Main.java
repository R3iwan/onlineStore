import java.util.*;
public class Main {
    // Factory Method Creational Pattern
    interface User {
        void purchase();
    }

    static class RegularUser implements User {
        public void purchase() {
            System.out.println("Regular user purchased product");
        }
    }

    static class PremiumUser implements User {
        public void purchase() {
            System.out.println("Premium user purchased product with discount");
        }
    }

    class UserFactory {
        public User getUser(String type) {
            if (type.equals("regular")) {
                return new RegularUser();
            } else if (type.equals("premium")) {
                return new PremiumUser();
            }
            return null;
        }
    }

    // Facade Structural Pattern
    class ProductSystem {
        private Map<String, Integer> products = new HashMap<>();

        public ProductSystem() {
            products.put("item1", 100);
            products.put("item2", 200);
            products.put("item3", 300);
        }

        public boolean checkAvailability(String product, int quantity) {
            Integer availableQuantity = products.get(product);
            if (availableQuantity != null && availableQuantity >= quantity) {
                return true;
            }
            return false;
        }

        public void purchaseProduct(String product, int quantity) {
            if (checkAvailability(product, quantity)) {
                products.put(product, products.get(product) - quantity);
                System.out.println("Successfully purchased " + quantity + " " + product + "(s)");
            } else {
                System.out.println("Product not available or insufficient quantity");
            }
        }
    }

    // Strategy Behavioral Pattern
    interface PaymentMethod {
        void pay(int amount);
    }

    class CreditCard implements PaymentMethod {
        public void pay(int amount) {
            System.out.println("Paying " + amount + " using Credit Card");
        }
    }

    class PayPal implements PaymentMethod {
        public void pay(int amount) {
            System.out.println("Paying " + amount + " using PayPal");
        }
    }

    class OnlineStore {
        private UserFactory userFactory = new UserFactory();
        private ProductSystem productSystem = new ProductSystem();
        private Scanner scanner = new Scanner(System.in);

        public void start() {
            System.out.print("Enter user type (regular/premium): ");
            String userType = scanner.nextLine();
            User user = userFactory.getUser(userType);

            System.out.print("Enter product name: ");
            String product = scanner.nextLine();
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();

            productSystem.purchaseProduct(product, quantity);

            System.out.print("Enter payment method (credit/paypal): ");
            String paymentMethod = scanner.next();
            PaymentMethod method;
            if (paymentMethod.equals("credit")) {
                method = new CreditCard();
            } else {
                method = new PayPal();
            }

            user.purchase();
            int amount = quantity * (user instanceof RegularUser ? 100 : 80);
            method.pay(amount);
        }
    }

    public class OnlineStoreApplication {
        public void main(String[] args) {
            OnlineStore store = new OnlineStore();
            store.start();
        }
    }


}
