class EventTicket {

    protected double basePrice;
    protected double amountPaid;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
        this.amountPaid = 0;
    }


    public void pay(double amount) {
        amountPaid += amount;
    }


    public double getBalanceDue() {
        return basePrice - amountPaid;
    }


    public String printTicket() {

        return "Standard | Balance: "
                + getBalanceDue();
    }
}


class WorkshopTicket extends EventTicket {

    private String track;

    public WorkshopTicket(
            double basePrice,
            String track) {

        super(basePrice);
        this.track = track;
    }


    public String getTrack() {
        return track;
    }


    @Override
    public String printTicket() {

        return "Workshop | Track: "
                + track
                + " | Balance: "
                + getBalanceDue();
    }
}


public class Problem4 {


    public static String batchPrint(
            EventTicket[] tickets) {

        StringBuilder result =
                new StringBuilder();


        for (EventTicket ticket : tickets) {

            // Polymorphic method call
            result.append(
                ticket.printTicket()
            );

            // Safe downcasting
            if (ticket instanceof WorkshopTicket) {

                WorkshopTicket workshop =
                        (WorkshopTicket) ticket;

                result.append(
                    " [Track via downcast: "
                    + workshop.getTrack()
                    + "]"
                );
            }

            result.append(" | ");
        }


        return result.toString();
    }


    public static void main(String[] args) {

        EventTicket standard =
                new EventTicket(500);

        WorkshopTicket workshop =
                new WorkshopTicket(
                    1200,
                    "AI/ML"
                );


        EventTicket[] tickets = {
            standard,
            workshop
        };


        System.out.println(
            batchPrint(tickets)
        );

        EventTicket plain =
                new EventTicket(500);

    }
}