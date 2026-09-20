class EventTicket {

    // Shared counter for every ticket
    private static int ticketsIssued = 0;

    // Cannot be changed after construction
    private final String ticketId;

    protected double basePrice;
    protected double amountPaid;


    public EventTicket(double basePrice) {

        this.basePrice = basePrice;
        this.amountPaid = 0;

        // Increment once for every object
        ticketsIssued++;

        // Create unique ticket ID
        ticketId = "TCK-" + (1000 + ticketsIssued);
    }


    public void pay(double amount) {

        amountPaid += amount;
    }


    // Overloaded method
    public void pay(double amount, String mode) {

        System.out.println(
            "Payment Mode: " + mode
        );

        // Reuse existing logic
        pay(amount);
    }


    public double getBalanceDue() {

        return basePrice - amountPaid;
    }


    public String getTicketId() {

        return ticketId;
    }


    public static int getTicketsIssued() {

        return ticketsIssued;
    }


    public static boolean isValidPromoCode(
            String code) {

        // Must have exactly 5 characters
        if (code == null || code.length() != 5) {
            return false;
        }


        // First character must be F
        if (code.charAt(0) != 'F') {
            return false;
        }


        // Next three characters must be digits
        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(3))) {
            return false;
        }


        // Last character must be uppercase
        if (!Character.isUpperCase(code.charAt(4))) {
            return false;
        }


        return true;
    }
}


// GroupTicket extends EventTicket
class GroupTicket extends EventTicket {

    private int groupSize;


    public GroupTicket(
            double basePrice,
            int groupSize) {

        super(basePrice);

        this.groupSize = groupSize;
    }


    public int getGroupSize() {

        return groupSize;
    }
}


public class Problem5 {


    public static String processNightlySettlement(
            EventTicket[] tickets) {

        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;


        for (EventTicket ticket : tickets) {

            // Handle null
            if (ticket == null) {
                nullSkipped++;
                continue;
            }


            processed++;


            if (ticket instanceof GroupTicket) {

                groupCount++;

            } else {

                individualCount++;
            }
        }


        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + groupCount
                + " group | "
                + individualCount
                + " individual";
    }


    public static void main(String[] args) {

        // --------------------------------
        // Ticket ID
        // --------------------------------

        EventTicket t1 =
                new EventTicket(500);

        System.out.println(
            t1.getTicketId()
        );

        System.out.println(
            EventTicket.getTicketsIssued()
        );


        // --------------------------------
        // Promo codes
        // --------------------------------

        System.out.println(
            EventTicket.isValidPromoCode("F123A")
        );

        System.out.println(
            EventTicket.isValidPromoCode("F12A")
        );

        System.out.println(
            EventTicket.isValidPromoCode("X123A")
        );


        // --------------------------------
        // Payment
        // --------------------------------

        t1.pay(200);

        t1.pay(200, "UPI");

        System.out.println(
            t1.getBalanceDue()
        );


        // --------------------------------
        // Settlement
        // --------------------------------

        EventTicket[] tickets = {

            new GroupTicket(2000, 5),

            null,

            new EventTicket(500)
        };


        System.out.println(
            processNightlySettlement(tickets)
        );
    }
}