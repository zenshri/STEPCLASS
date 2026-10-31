import java.util.*;

interface PricingPlan {
    double calculatePrice(double price);
}

class DayScholarPlan implements PricingPlan {
    public double calculatePrice(double price) {
        return price;
    }
}

class HostellerPlan implements PricingPlan {
    public double calculatePrice(double price) {
        return price * 0.90;
    }
}

class StaffPlan implements PricingPlan {
    public double calculatePrice(double price) {
        return price * 0.80;
    }
}

class Transaction {
    String description;
    double amount;

    Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }
}

class Purchase {
    String itemName;
    double chargedAmount;
    boolean refunded = false;

    Purchase(String itemName, double amount) {
        this.itemName = itemName;
        this.chargedAmount = amount;
    }
}

class SmartCard {
    private String cardId;
    private PricingPlan plan;
    private List<Transaction> transactions = new ArrayList<>();
    private List<Purchase> purchases = new ArrayList<>();
    private boolean blocked = false;

    SmartCard(String cardId, PricingPlan plan) {
        this.cardId = cardId;
        this.plan = plan;
    }

    private double getBalance() {
        double balance = 0;

        for (Transaction transaction : transactions) {
            balance += transaction.amount;
        }

        return balance;
    }

    void topUp(double amount) {

        if (blocked) {
            System.out.println("Top-up failed: Card is blocked.");
            return;
        }

        if (amount < 100) {
            System.out.println("Top-up failed: Minimum top-up is ₹100.");
            return;
        }

        if (getBalance() + amount > 5000) {
            System.out.println("Top-up failed: Maximum balance is ₹5000.");
            return;
        }

        transactions.add(new Transaction("Top-up", amount));

        System.out.printf("%s topped up with ₹%.2f.%n",
                cardId, amount);

        System.out.printf("Balance: ₹%.2f%n", getBalance());
    }

    Purchase purchase(String item, double originalPrice) {

        if (blocked) {
            System.out.println("Purchase failed: Card is blocked.");
            return null;
        }

        if (originalPrice < 0) {
            System.out.println("Purchase failed: Invalid price.");
            return null;
        }

        double chargedAmount = plan.calculatePrice(originalPrice);

        if (chargedAmount > getBalance()) {
            System.out.printf(
                    "Purchase failed: Insufficient balance "
                    + "(required ₹%.2f, available ₹%.2f).%n",
                    chargedAmount, getBalance());
            return null;
        }

        transactions.add(
                new Transaction(item, -chargedAmount));

        Purchase purchase = new Purchase(item, chargedAmount);
        purchases.add(purchase);

        System.out.printf("%s purchased for ₹%.2f.%n",
                item, chargedAmount);

        System.out.printf("Balance: ₹%.2f%n", getBalance());

        return purchase;
    }

    void refund(Purchase purchase) {

        if (purchase == null || !purchases.contains(purchase)) {
            System.out.println("Refund rejected: Invalid purchase.");
            return;
        }

        if (purchase.refunded) {
            System.out.println("Refund rejected: " + purchase.itemName
                    + " has already been refunded.");
            return;
        }

        purchase.refunded = true;

        transactions.add(new Transaction(
                "Refund: " + purchase.itemName,
                purchase.chargedAmount));

        System.out.printf("Refund of ₹%.2f for %s processed.%n",
                purchase.chargedAmount, purchase.itemName);

        System.out.printf("Balance: ₹%.2f%n", getBalance());
    }

    void block() {
        blocked = true;
        System.out.println(cardId + " blocked.");
    }

    void unblock() {
        blocked = false;
        System.out.println(cardId + " unblocked.");
    }

    void miniStatement() {
        System.out.println("Mini-statement for " + cardId + ":");

        for (Transaction transaction : transactions) {
            System.out.printf("%+.2f, ", transaction.amount);
        }

        System.out.printf("= ₹%.2f.%n", getBalance());
    }
}

public class CampusCanteenSmartCard {
    public static void main(String[] args) {

        SmartCard card = new SmartCard(
                "C-2045", new HostellerPlan());

        card.topUp(500);

        Purchase p1 = card.purchase("Veg Thali", 120);

        Purchase p2 = card.purchase("Cold Coffee", 60);

        card.purchase("Other Items", 400);

        card.refund(p1);

        card.refund(p1);

        card.miniStatement();
    }
}