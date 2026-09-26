import java.util.ArrayList;
import java.util.List;

public class ShoppingPaymentSystem {
    private static final class Customer {
        private final String name;

        private Customer(String name) {
            this.name = name;
        }
    }

    private static final class Product {
        private final String name;
        private final double price;

        private Product(String name, double price) {
            if (price < 0) {
                throw new IllegalArgumentException("Product price cannot be negative.");
            }
            this.name = name;
            this.price = price;
        }
    }

    private static final class OrderItem {
        private final Product product;
        private final int quantity;

        private OrderItem(Product product, int quantity) {
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be positive.");
            }
            this.product = product;
            this.quantity = quantity;
        }

        private double subtotal() {
            return product.price * quantity;
        }
    }

    private enum OrderStatus {
        PENDING,
        PAID
    }

    private static final class Order {
        private final String id;
        private final Customer customer;
        private final List<OrderItem> items = new ArrayList<>();
        private OrderStatus status = OrderStatus.PENDING;

        private Order(String id, Customer customer) {
            this.id = id;
            this.customer = customer;
        }

        private void addProduct(Product product, int quantity) {
            if (status == OrderStatus.PAID) {
                throw new IllegalStateException("Cannot change a paid order.");
            }
            items.add(new OrderItem(product, quantity));
        }

        private double total() {
            return items.stream().mapToDouble(OrderItem::subtotal).sum();
        }
    }

    private interface PaymentMethod {
        boolean processPayment(Order order);
        String name();
    }

    private static final class CreditCardPayment implements PaymentMethod {
        @Override
        public boolean processPayment(Order order) {
            return true;
        }

        @Override
        public String name() {
            return "Credit Card";
        }
    }

    private static final class PayPalPayment implements PaymentMethod {
        @Override
        public boolean processPayment(Order order) {
            return false;
        }

        @Override
        public String name() {
            return "PayPal";
        }
    }

    private static final class BankTransferPayment implements PaymentMethod {
        @Override
        public boolean processPayment(Order order) {
            return true;
        }

        @Override
        public String name() {
            return "Bank Transfer";
        }
    }

    private static final class PaymentService {
        private void pay(Order order, PaymentMethod paymentMethod) {
            if (order.items.isEmpty()) {
                System.out.println("Cannot process payment for an empty order.");
                return;
            }
            if (order.status == OrderStatus.PAID) {
                System.out.println("Order " + order.id + " is already paid.");
                return;
            }

            System.out.println("Payment initiated via " + paymentMethod.name() + " for Order " + order.id + ".");
            if (paymentMethod.processPayment(order)) {
                order.status = OrderStatus.PAID;
                System.out.printf("Payment for Order %s successful ($%.2f). Status: %s.%n",
                        order.id, order.total(), order.status);
            } else {
                System.out.println("Payment for Order " + order.id + " failed. Status: " + order.status + ".");
            }
        }
    }

    private static Order createOrder(String id, Customer customer) {
        System.out.println("Order created for " + customer.name + ".");
        return new Order(id, customer);
    }

    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        Customer customerX = new Customer("Customer X");
        Order orderX = createOrder("X", customerX);
        orderX.addProduct(new Product("Product A", 10.0), 2);
        orderX.addProduct(new Product("Product B", 15.0), 1);
        paymentService.pay(orderX, new CreditCardPayment());

        Order orderY = createOrder("Y", new Customer("Customer Y"));
        paymentService.pay(orderY, new CreditCardPayment());

        Order orderZ = createOrder("Z", new Customer("Customer Z"));
        orderZ.addProduct(new Product("Product C", 20.0), 1);
        paymentService.pay(orderZ, new PayPalPayment());
    }
}