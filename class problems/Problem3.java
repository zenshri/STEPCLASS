import java.util.Arrays;

class EventTicket3 {

    protected double basePrice;
    protected double amountPaid;

    private double[] lateFeeHistory;
    private int lateFeeCount;


    public EventTicket3(double basePrice) {

        this.basePrice = basePrice;
        this.amountPaid = 0;

        lateFeeHistory = new double[10];
        lateFeeCount = 0;
    }


    public void pay(double amount) {
        amountPaid += amount;
    }


    public double getBalanceDue() {
        return basePrice - amountPaid;
    }


    protected void applyLateFee(double amount) {

        // Increase amount owed
        basePrice += amount;

        // Record penalty
        if (lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount] = amount;
            lateFeeCount++;
        }
    }


    public double[] getLateFeeHistory() {

        return Arrays.copyOf(
            lateFeeHistory,
            lateFeeCount
        );
    }
}


class WorkshopTicket3 extends EventTicket3 {

    public WorkshopTicket3(double basePrice) {
        super(basePrice);
    }


    @Override
    protected void applyLateFee(double amount) {

        // Double the penalty
        super.applyLateFee(amount * 2);
    }
}


public class Problem3 {

    public static void main(String[] args) {

        WorkshopTicket3 w =
                new WorkshopTicket3(1200);

        w.pay(1200);

        w.applyLateFee(100);

        System.out.println(
            w.getBalanceDue()
        );


        double[] history =
                w.getLateFeeHistory();

        System.out.println(
            Arrays.toString(history)
        );


        // Try to tamper with returned array
        history[0] = 999;

        System.out.println(
            Arrays.toString(
                w.getLateFeeHistory()
            )
        );
    }
}