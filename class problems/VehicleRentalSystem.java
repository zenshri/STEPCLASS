abstract class Vehicle {
    String name;
    boolean available = true;

    Vehicle(String name) {
        this.name = name;
    }

    abstract double calculateCharge(int days);
}

class LuxuryCar extends Vehicle {
    LuxuryCar(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 100;
    }
}

class StandardCar extends Vehicle {
    StandardCar(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 50;
    }
}

class SUV extends Vehicle {
    SUV(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 80;
    }
}

class Rental {
    Vehicle vehicle;
    int days;
    boolean active;

    Rental(Vehicle vehicle, int days) {
        this.vehicle = vehicle;
        this.days = days;
        this.active = true;
    }

    void returnVehicle() {
        if (active) {
            active = false;
            vehicle.available = true;
            System.out.println(vehicle.name + " returned. Now available.");
        }
    }
}

class RentalService {

    Rental rentVehicle(Vehicle vehicle, int days) {
        if (!vehicle.available) {
            System.out.println(vehicle.name + " is not available.");
            return null;
        }

        vehicle.available = false;

        Rental rental = new Rental(vehicle, days);

        System.out.println(vehicle.name + " rented for " + days + " days.");
        System.out.printf("Total charge: $%.2f%n",
                vehicle.calculateCharge(days));

        return rental;
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {

        Vehicle luxury = new LuxuryCar("Luxury Car A");
        Vehicle standard = new StandardCar("Standard Car B");

        RentalService service = new RentalService();

        Rental r1 = service.rentVehicle(luxury, 3);
        Rental r2 = service.rentVehicle(standard, 5);

        if (r1 != null) {
            r1.returnVehicle();
        }
    }
}