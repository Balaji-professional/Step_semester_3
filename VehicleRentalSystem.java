import java.util.HashMap;
import java.util.Map;

public class VehicleRentalSystem {
    private static final class Customer {
        private final String name;

        private Customer(String name) {
            this.name = name;
        }
    }

    private abstract static class Vehicle {
        private final String id;
        private boolean available = true;

        private Vehicle(String id) {
            this.id = id;
        }

        abstract double calculateRentalCharge(int days);
    }

    private static final class Sedan extends Vehicle {
        private Sedan(String id) {
            super(id);
        }

        @Override
        double calculateRentalCharge(int days) {
            return days * 50.0;
        }
    }

    private static final class SUV extends Vehicle {
        private SUV(String id) {
            super(id);
        }

        @Override
        double calculateRentalCharge(int days) {
            return days * 80.0;
        }
    }

    private static final class Rental {
        private final Customer customer;
        private final Vehicle vehicle;
        private final int days;

        private Rental(Customer customer, Vehicle vehicle, int days) {
            this.customer = customer;
            this.vehicle = vehicle;
            this.days = days;
        }
    }

    private static final class RentalService {
        private final Map<String, Vehicle> vehicles = new HashMap<>();
        private final Map<String, Rental> activeRentals = new HashMap<>();

        private void addVehicle(Vehicle vehicle) {
            vehicles.put(vehicle.id, vehicle);
        }

        private void rent(Customer customer, String vehicleId, int days) {
            Vehicle vehicle = vehicles.get(vehicleId);
            if (vehicle == null || days <= 0) {
                System.out.println("Rental could not be created: invalid vehicle or duration.");
                return;
            }
            if (!vehicle.available) {
                System.out.println(vehicleId + " is currently unavailable.");
                return;
            }

            vehicle.available = false;
            activeRentals.put(vehicleId, new Rental(customer, vehicle, days));
            System.out.println(vehicleId + " rented successfully by " + customer.name + ".");
            System.out.printf("Rental charge: $%.2f%n", vehicle.calculateRentalCharge(days));
        }

        private void returnVehicle(Customer customer, String vehicleId) {
            Rental rental = activeRentals.get(vehicleId);
            if (rental == null || !rental.customer.name.equals(customer.name)) {
                System.out.println("No active rental for " + vehicleId + " belongs to " + customer.name + ".");
                return;
            }

            activeRentals.remove(vehicleId);
            rental.vehicle.available = true;
            System.out.println(vehicleId + " returned by " + customer.name + ".");
        }
    }

    public static void main(String[] args) {
        Customer customer1 = new Customer("Customer 1");
        Customer customer2 = new Customer("Customer 2");
        Customer customer3 = new Customer("Customer 3");
        RentalService service = new RentalService();
        service.addVehicle(new Sedan("Sedan A"));
        service.addVehicle(new SUV("SUV B"));

        service.rent(customer1, "Sedan A", 3);
        service.rent(customer2, "Sedan A", 2);
        service.returnVehicle(customer1, "Sedan A");
        service.rent(customer3, "SUV B", 5);
    }
}