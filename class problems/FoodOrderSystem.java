import java.util.*;

interface PaymentMethod {
    boolean pay(double amount);
}

class CreditCardPayment implements PaymentMethod {
    public boolean pay(double amount) {
        System.out.println("Payment via Credit Card successful.");
        return true;
    }
}

class DigitalWalletPayment implements PaymentMethod {
    public boolean pay(double amount) {
        System.out.println("Payment via Digital Wallet failed.");
        return false;
    }
}

class CashOnDelivery implements PaymentMethod {
    public boolean pay(double amount) {
        System.out.println("Cash on Delivery selected.");
        return true;
    }
}

class FoodItem {
    String name;
    double price;

    FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class LineItem {
    FoodItem item;
    int quantity;

    LineItem(FoodItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    double getTotal() {
        return item.price * quantity;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }

    void notifyCustomer(String message) {
        System.out.println("Notification: " + message);
    }
}

class FoodOrder {
    static int count = 122;

    int orderId;
    Customer customer;
    List<LineItem> items = new ArrayList<>();
    String status = "Pending Payment";

    FoodOrder(Customer customer) {
        this.customer = customer;
        this.orderId = ++count;
        System.out.println("Order created.");
    }

    void addItem(FoodItem item, int quantity) {
        items.add(new LineItem(item, quantity));

        System.out.println("Added " + item.name
                + " (Qty " + quantity + ")");
    }

    double getTotal() {
        double total = 0;

        for (LineItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    void placeOrder(PaymentMethod payment) {

        if (items.isEmpty()) {
            System.out.println("Cannot place order: Order must contain at least one item.");
            return;
        }

        System.out.println("Order placed successfully.");

        if (payment.pay(getTotal())) {
            status = "Paid";
            System.out.println("Order status: " + status);

            customer.notifyCustomer(
                    "Order #" + orderId + " placed and paid.");
        } else {
            status = "Pending Payment";
            System.out.println("Order status: " + status);

            customer.notifyCustomer(
                    "Order #" + orderId + " placed, awaiting payment.");
        }
    }
}

public class FoodOrderSystem {
    public static void main(String[] args) {

        Customer customer = new Customer("John");

        FoodOrder order1 = new FoodOrder(customer);

        order1.addItem(new FoodItem("Pizza", 200), 2);
        order1.addItem(new FoodItem("Soda", 50), 1);

        FoodOrder emptyOrder = new FoodOrder(customer);
        emptyOrder.placeOrder(new CreditCardPayment());

        order1.placeOrder(new CreditCardPayment());

        FoodOrder order2 = new FoodOrder(customer);

        order2.addItem(new FoodItem("Burger", 150), 1);

        order2.placeOrder(new DigitalWalletPayment());
    }
}