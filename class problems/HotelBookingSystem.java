import java.time.LocalDate;
import java.util.*;

abstract class Room {
    String name;

    Room(String name) {
        this.name = name;
    }

    abstract double getPricePerDay();
}

class DeluxeRoom extends Room {
    DeluxeRoom(String name) {
        super(name);
    }

    double getPricePerDay() {
        return 200;
    }
}

class StandardRoom extends Room {
    StandardRoom(String name) {
        super(name);
    }

    double getPricePerDay() {
        return 150;
    }
}

class Reservation {
    Room room;
    LocalDate startDate, endDate;
    boolean active = true;

    Reservation(Room room, LocalDate start, LocalDate end) {
        this.room = room;
        this.startDate = start;
        this.endDate = end;
    }

    void cancel() {
        active = false;
        System.out.println("Reservation for " + room.name
                + " cancelled successfully.");
    }
}

class Hotel {
    List<Reservation> reservations = new ArrayList<>();

    Reservation book(Room room, LocalDate start, LocalDate end) {

        for (Reservation r : reservations) {
            if (r.room == room && r.active &&
                start.isBefore(r.endDate) &&
                end.isAfter(r.startDate)) {

                System.out.println("Booking failed: " + room.name
                        + " is not available for " + start + " to " + end);
                return null;
            }
        }

        Reservation reservation = new Reservation(room, start, end);
        reservations.add(reservation);

        long days = java.time.temporal.ChronoUnit.DAYS.between(start, end);
        double price = days * room.getPricePerDay();

        System.out.println(room.name + " booked from "
                + start + " to " + end + ".");

        System.out.printf("Total price: $%.2f%n", price);

        return reservation;
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {

        Hotel hotel = new Hotel();

        Room deluxe = new DeluxeRoom("Deluxe Room 101");
        Room standard = new StandardRoom("Standard Room 205");

        LocalDate d1 = LocalDate.parse("2024-12-01");
        LocalDate d2 = LocalDate.parse("2024-12-05");

        LocalDate d3 = LocalDate.parse("2024-12-03");
        LocalDate d4 = LocalDate.parse("2024-12-07");

        Reservation r1 = hotel.book(deluxe, d1, d2);
        hotel.book(standard, d3, d4);
        hotel.book(deluxe, d3, d4);

        if (r1 != null) {
            r1.cancel();
        }
    }
}