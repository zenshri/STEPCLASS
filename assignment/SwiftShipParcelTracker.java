import java.util.*;

interface ShippingType {
    double calculateCharge(double weight);
}

class StandardShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return 40 + 10 * weight;
    }
}

class ExpressShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return 80 + 15 * weight;
    }
}

class FragileShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return 40 + 10 * weight + 50;
    }
}

interface NotificationChannel {
    void send(String message);
}

class SmsChannel implements NotificationChannel {
    public void send(String message) {
        System.out.println("[SMS] " + message);
    }
}

class EmailChannel implements NotificationChannel {
    public void send(String message) {
        System.out.println("[Email] " + message);
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Parcel {
    String id;
    double weight;
    String status = "BOOKED";
    boolean cancelled = false;

    ShippingType shippingType;
    List<NotificationChannel> channels = new ArrayList<>();

    Parcel(String id, double weight, ShippingType type) {
        this.id = id;
        this.weight = weight;
        this.shippingType = type;
    }

    void subscribe(NotificationChannel channel) {
        channels.add(channel);
    }

    void notifyChannels() {
        for (NotificationChannel channel : channels) {
            channel.send(id + " is now " + status + ".");
        }
    }

    void updateStatus(String newStatus) {

        if (cancelled) {
            System.out.println("Parcel has been cancelled.");
            return;
        }

        String[] statuses = {
            "BOOKED", "PICKED_UP", "IN_TRANSIT",
            "OUT_FOR_DELIVERY", "DELIVERED"
        };

        int current = Arrays.asList(statuses).indexOf(status);
        int next = Arrays.asList(statuses).indexOf(newStatus);

        if (next != current + 1) {
            System.out.println("Invalid transition: " + status
                    + " → " + newStatus + " is not allowed.");
            return;
        }

        status = newStatus;
        notifyChannels();
    }

    void cancel() {
        if (!status.equals("BOOKED")) {
            System.out.println("Cancellation failed: " + id
                    + " can be cancelled only while BOOKED.");
            return;
        }

        cancelled = true;
        System.out.println(id + " cancelled successfully.");
    }
}

class ParcelService {
    Parcel book(String id, double weight, ShippingType type) {

        Parcel parcel = new Parcel(id, weight, type);

        System.out.println("Parcel " + id + " booked ("
                + type.getClass().getSimpleName().replace("Shipping", "")
                + ", " + weight + " kg).");

        System.out.printf("Charge: ₹%.2f%n",
                type.calculateCharge(weight));

        return parcel;
    }
}

public class SwiftShipParcelTracker {
    public static void main(String[] args) {

        ParcelService service = new ParcelService();

        Parcel parcel = service.book(
                "P101", 2, new ExpressShipping());

        parcel.subscribe(new SmsChannel());
        parcel.subscribe(new EmailChannel());

        parcel.notifyChannels();

        parcel.updateStatus("PICKED_UP");

        parcel.cancel();

        parcel.updateStatus("IN_TRANSIT");

        parcel.updateStatus("DELIVERED");
    }
}