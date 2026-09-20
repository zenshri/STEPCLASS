class RaceEntry {

    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;

    public RaceEntry(String bibNumber, double entryFee) {

        if (bibNumber == null ||
            bibNumber.trim().isEmpty() ||
            bibNumber.trim().length() < 4) {

            throw new IllegalArgumentException("Invalid bib number");
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.amountPaid = 0;
    }

    public void pay(double amount) {
        amountPaid += amount;
    }

    public double getBalanceDue() {
        return entryFee - amountPaid;
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

    public String getCategory() {
        return category;
    }
}


public class Problem1 {

    public static String registerBatch(
            String[] bibNumbers,
            double entryFee) {

        int registered = 0;
        int rejected = 0;

        for (String bib : bibNumbers) {

            try {
                new RaceEntry(bib, entryFee);
                registered++;

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered
                + " | Rejected: " + rejected;
    }


    public static void main(String[] args) {

        // Test 1
        try {

            new RaceEntry("B1", 50);

        } catch (IllegalArgumentException e) {

            System.out.println("construction rejected");
        }


        // Test 2
        RunnerEntry r =
                new RunnerEntry(
                    "BIB2001",
                    80,
                    "Open 10K"
                );

        r.pay(30);

        System.out.println(
            r.getBalanceDue()
        );


        // Test 3
        String[] bibNumbers = {
            "BIB1",
            "B1",
            "BIB2"
        };

        System.out.println(
            registerBatch(bibNumbers, 80)
        );
    }
}