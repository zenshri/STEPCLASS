class EventTicket1 {
    protected String attendeeId;
    protected double basePrice;
    protected double amountPaid;

    public EventTicket1(String attendeeId, double basePrice) {

        if (attendeeId == null ||
            attendeeId.trim().isEmpty() ||
            attendeeId.trim().length() < 4) {

            throw new IllegalArgumentException("Invalid attendee ID");
        }

        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        this.amountPaid = 0;
    }

    public void pay(double amount) {
        amountPaid += amount;
    }

    public double getBalanceDue() {
        return basePrice - amountPaid;
    }
}


class WorkshopTicket1 extends EventTicket1 {

    private String track;

    public WorkshopTicket1(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }

    public String getTrack() {
        return track;
    }
}


public class Problem1 {

    public static String registerBatch(
            String[] attendeeIds,
            double basePrice) {

        int registered = 0;
        int rejected = 0;

        for (String id : attendeeIds) {

            try {
                new EventTicket1(id, basePrice);
                registered++;

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered +
               " | Rejected: " + rejected;
    }


    public static void main(String[] args) {

        // Test 1
        try {
            EventTicket1 t = new EventTicket1("ST1", 500);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        // Test 2
        WorkshopTicket1 w =
                new WorkshopTicket1("STU2", 1200, "AI/ML");

        w.pay(500);

        System.out.println(w.getBalanceDue());

        // Test 3
        String[] ids = {
            "STU1",
            "ST1",
            "STU2",
            " ",
            "STU3"
        };

        System.out.println(
            registerBatch(ids, 500)
        );
    }
}