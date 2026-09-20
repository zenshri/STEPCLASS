class EventTicket2 {

    protected String attendeeId;
    protected double basePrice;
    protected double amountPaid;

    public EventTicket2(String attendeeId, double basePrice) {

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

    public void printTicket() {
        System.out.println(
            "Standard Event Ticket | Balance Due: "
            + getBalanceDue()
        );
    }
}


// Level 2
class WorkshopTicket2 extends EventTicket2 {

    protected String track;

    public WorkshopTicket2(
            String attendeeId,
            double basePrice,
            String track) {

        super(attendeeId, basePrice);
        this.track = track;
    }

    @Override
    public void printTicket() {

        System.out.println(
            "Workshop Ticket | Track: "
            + track
            + " | Balance Due: "
            + getBalanceDue()
        );
    }
}


// Level 3
class PremiumWorkshopTicket1 extends WorkshopTicket2 {

    private double kitFee;

    public PremiumWorkshopTicket1(
            String attendeeId,
            double basePrice,
            String track,
            double kitFee) {

        super(attendeeId, basePrice, track);
        this.kitFee = kitFee;
    }

    @Override
    public void printTicket() {

        System.out.println(
            "Premium Workshop Ticket | Track: "
            + track
            + " | Kit Fee: "
            + kitFee
            + " | Balance Due: "
            + getBalanceDue()
        );
    }
}


// Hierarchical sibling of WorkshopTicket
class HackathonTicket1 extends EventTicket2 {

    private String teamName;

    public HackathonTicket1(
            String attendeeId,
            double basePrice,
            String teamName) {

        super(attendeeId, basePrice);
        this.teamName = teamName;
    }

    @Override
    public void printTicket() {

        System.out.println(
            "Hackathon Ticket | Team: "
            + teamName
            + " | Balance Due: "
            + getBalanceDue()
        );
    }
}


public class Problem2 {

    public static String classifyGeneration(
            EventTicket2 ticket) {

        if (ticket instanceof PremiumWorkshopTicket1) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (ticket instanceof HackathonTicket1) {
            return "Hierarchical sibling (independent branch)";
        }

        if (ticket instanceof WorkshopTicket2) {
            return "Direct Workshop descendant";
        }

        return "Standard EventTicket";
    }


    public static double getTotalBalanceDue(
            EventTicket2[] tickets) {

        double total = 0;

        for (EventTicket2 ticket : tickets) {
            total += ticket.getBalanceDue();
        }

        return total;
    }


    public static void main(String[] args) {

        EventTicket2 standardTicket =
                new EventTicket2("STU1", 500);

        WorkshopTicket2 workshopTicket =
                new WorkshopTicket2(
                    "STU2",
                    1200,
                    "AI/ML"
                );

        PremiumWorkshopTicket1 premiumTicket =
                new PremiumWorkshopTicket1(
                    "STU3",
                    2000,
                    "Cloud Native",
                    300
                );

        HackathonTicket1 hackathonTicket =
                new HackathonTicket1(
                    "STU4",
                    800,
                    "Byte Force"
                );


        standardTicket.printTicket();
        workshopTicket.printTicket();
        premiumTicket.printTicket();
        hackathonTicket.printTicket();


        System.out.println(
            classifyGeneration(premiumTicket)
        );

        System.out.println(
            classifyGeneration(hackathonTicket)
        );


        EventTicket2[] tickets = {
            standardTicket,
            workshopTicket,
            premiumTicket,
            hackathonTicket
        };

        System.out.println(
            getTotalBalanceDue(tickets)
        );
    }
}