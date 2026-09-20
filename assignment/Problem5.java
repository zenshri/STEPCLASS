class RaceEntry {

    // Shared counter
    private static int bibCounter = 0;

    // Cannot be reassigned
    private final String entryCode;

    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;


    public RaceEntry(
            String bibNumber,
            double entryFee) {

        // Validation must happen BEFORE counter increment
        if (bibNumber == null ||
            bibNumber.trim().isEmpty() ||
            bibNumber.trim().length() < 4) {

            throw new IllegalArgumentException(
                "Invalid bib number"
            );
        }


        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.amountPaid = 0;


        // Increment exactly once
        bibCounter++;


        // Build final code
        entryCode =
                "BIB-" + (1000 + bibCounter);
    }


    public void pay(double amount) {

        amountPaid += amount;
    }


    // Overloaded method
    public void pay(
            double amount,
            String mode) {

        System.out.println(
            "Paying via " + mode
        );

        // Reuse one-argument version
        pay(amount);
    }


    public double getBalanceDue() {

        return entryFee - amountPaid;
    }


    public String getEntryCode() {

        return entryCode;
    }


    public static int getBibCounter() {

        return bibCounter;
    }


    public static boolean isValidDiscountCode(
            String code) {

        // Check length first
        if (code == null ||
            code.length() != 5) {

            return false;
        }


        // M
        if (code.charAt(0) != 'M') {

            return false;
        }


        // Three digits
        if (!Character.isDigit(code.charAt(1))) {

            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {

            return false;
        }

        if (!Character.isDigit(code.charAt(3))) {

            return false;
        }


        // Uppercase letter
        if (!Character.isUpperCase(
                code.charAt(4))) {

            return false;
        }


        return true;
    }
}


// Relay subclass
class RelayTeamEntry extends RaceEntry {

    private int teamSize;


    public RelayTeamEntry(
            String bibNumber,
            double entryFee,
            int teamSize) {

        super(bibNumber, entryFee);

        this.teamSize = teamSize;
    }


    public int getTeamSize() {

        return teamSize;
    }
}


public class Problem5 {

    public static String settleNight(
            RaceEntry[] entries) {

        int processed = 0;
        int nullSkipped = 0;
        int relayCount = 0;
        int individualCount = 0;


        for (RaceEntry entry : entries) {

            // Handle null
            if (entry == null) {

                nullSkipped++;
                continue;
            }


            processed++;


            if (entry instanceof RelayTeamEntry) {

                relayCount++;

            } else {

                individualCount++;
            }
        }


        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + relayCount
                + " relay | "
                + individualCount
                + " individual";
    }


    public static void main(String[] args) {

        // -----------------------------
        // Discount codes
        // -----------------------------

        System.out.println(
            RaceEntry.isValidDiscountCode(
                "M123A"
            )
        );

        System.out.println(
            RaceEntry.isValidDiscountCode(
                "M12A"
            )
        );

        System.out.println(
            RaceEntry.isValidDiscountCode(
                "X123A"
            )
        );


        // -----------------------------
        // Create entries
        // -----------------------------

        RaceEntry eliteEntry =
                new RaceEntry(
                    "BIB3001",
                    150
                );


        RelayTeamEntry relayEntry =
                new RelayTeamEntry(
                    "BIB4001",
                    300,
                    4
                );


        // -----------------------------
        // Payment
        // -----------------------------

        eliteEntry.pay(
            10,
            "UPI"
        );


        // -----------------------------
        // Night settlement
        // -----------------------------

        RaceEntry[] entries = {
            eliteEntry,
            null,
            relayEntry
        };


        System.out.println(
            settleNight(entries)
        );


        // -----------------------------
        // Counter
        // -----------------------------

        System.out.println(
            RaceEntry.getBibCounter()
        );
    }
}