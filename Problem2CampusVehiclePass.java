import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem2CampusVehiclePass {
    private interface Chargeable {
        boolean bookChargingBay();
    }

    private abstract static class Vehicle {
        private final String passNumber;
        private final String ownerName;

        private Vehicle(String passNumber, String ownerName) {
            this.passNumber = passNumber;
            this.ownerName = ownerName;
        }

        abstract String type();

        abstract int yearlyPassFee();
    }

    private static final class Bike extends Vehicle {
        private Bike(String passNumber, String ownerName) {
            super(passNumber, ownerName);
        }

        @Override
        String type() {
            return "Bike";
        }

        @Override
        int yearlyPassFee() {
            return 300;
        }
    }

    private static final class Car extends Vehicle {
        private Car(String passNumber, String ownerName) {
            super(passNumber, ownerName);
        }

        @Override
        String type() {
            return "Car";
        }

        @Override
        int yearlyPassFee() {
            return 1000;
        }
    }

    private static final class ElectricBike extends Vehicle implements Chargeable {
        private ElectricBike(String passNumber, String ownerName) {
            super(passNumber, ownerName);
        }

        @Override
        String type() {
            return "E-Bike";
        }

        @Override
        int yearlyPassFee() {
            return 300;
        }

        @Override
        public boolean bookChargingBay() {
            return true;
        }
    }

    private static final class ElectricCar extends Vehicle implements Chargeable {
        private ElectricCar(String passNumber, String ownerName) {
            super(passNumber, ownerName);
        }

        @Override
        String type() {
            return "ECar";
        }

        @Override
        int yearlyPassFee() {
            return 1000;
        }

        @Override
        public boolean bookChargingBay() {
            return true;
        }
    }

    public static void main(String[] args) {
        Map<String, Vehicle> vehicles = new HashMap<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\s+");
                switch (parts[0]) {
                    case "PASS":
                        if (parts.length != 4) {
                            throw new IllegalArgumentException("Pass format: PASS <VehicleType> <PassNumber> <OwnerName>");
                        }
                        Vehicle vehicle = createVehicle(parts[1], parts[2], parts[3]);
                        vehicles.put(parts[2], vehicle);
                        System.out.println(vehicle.passNumber + " (" + vehicle.type() + ") pass fee "
                            + vehicle.yearlyPassFee());
                        break;
                    case "CHARGE":
                        if (parts.length != 2) {
                            throw new IllegalArgumentException("Charge format: CHARGE <PassNumber>");
                        }
                        Vehicle registeredVehicle = vehicles.get(parts[1]);
                        if (registeredVehicle == null) {
                            System.out.println(parts[1] + " rejected: vehicle not found");
                        } else if (registeredVehicle instanceof Chargeable
                                && ((Chargeable) registeredVehicle).bookChargingBay()) {
                            System.out.println(parts[1] + " charging bay allotted");
                        } else {
                            System.out.println(parts[1] + " rejected: charging unsupported");
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operation: " + parts[0]);
                }
            }
        }
    }

    private static Vehicle createVehicle(String vehicleType, String passNumber, String ownerName) {
        switch (vehicleType.toLowerCase().replace("-", "")) {
            case "bike":
                return new Bike(passNumber, ownerName);
            case "car":
                return new Car(passNumber, ownerName);
            case "ebike":
                return new ElectricBike(passNumber, ownerName);
            case "ecar":
                return new ElectricCar(passNumber, ownerName);
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }
    }
}
