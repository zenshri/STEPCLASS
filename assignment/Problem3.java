import java.util.Arrays;

class RaceEntry {

    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;

    private double[] lateFeeHistory;
    private int lateFeeCount;


    public RaceEntry(String bibNumber, double entryFee) {

        if (bibNumber == null ||
            bibNumber.trim().isEmpty() ||
            bibNumber.trim().length() < 4) {

            throw new IllegalArgumentException("Invalid bib number");
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.amountPaid = 0;

        lateFeeHistory = new double[10];
        lateFeeCount = 0;
    }


    public void pay(double amount) {

        amountPaid += amount;
    }


    public double getBalanceDue() {

        return entryFee - amountPaid;
    }


    protected void applyLateFee(double amount) {

        // Add penalty to amount due
        entryFee += amount;

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


class RunnerEntry extends RaceEntry {

    private String category;


    public RunnerEntry(
            String bibNumber,
            double entryFee,
            String category) {

        super(bibNumber, entryFee);
        this.category = category;
    }


    @Override
    protected void applyLateFee(double amount) {

        super.applyLateFee(amount * 2);
    }
}


public class Problem3 {

    public static void main(String[] args) {

        RunnerEntry r =
                new RunnerEntry(
                    "BIB2001",
                    80,
                    "Open 10K"
                );


        r.pay(30);

        r.applyLateFee(20);


        System.out.println(
            r.getBalanceDue()
        );


        double[] history =
                r.getLateFeeHistory();

        System.out.println(
            Arrays.toString(history)
        );


        // Attempt to modify returned array
        history[0] = 999;


        // Original history remains unchanged
        System.out.println(
            Arrays.toString(
                r.getLateFeeHistory()
            )
        );
    }
}