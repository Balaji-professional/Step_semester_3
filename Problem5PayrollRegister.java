import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem5PayrollRegister {
    private abstract static class StaffMember {
        private final String name;
        private final String type;

        private StaffMember(String name, String type) {
            this.name = name;
            this.type = type;
        }

        abstract long pay();

        @Override
        public String toString() {
            return "Payslip[name=" + name + ", type=" + type + ", pay=" + pay() + "]";
        }
    }

    private static final class FullTime extends StaffMember {
        private final long salary;

        private FullTime(String name, long salary) {
            super(name, "FullTime");
            this.salary = salary;
        }

        @Override
        long pay() {
            return salary;
        }
    }

    private static final class PartTime extends StaffMember {
        private final long hours;
        private final long hourlyRate;

        private PartTime(String name, long hours, long hourlyRate) {
            super(name, "PartTime");
            this.hours = hours;
            this.hourlyRate = hourlyRate;
        }

        @Override
        long pay() {
            return Math.multiplyExact(hours, hourlyRate);
        }
    }

    private static final class Intern extends StaffMember {
        private final long stipend;

        private Intern(String name, long stipend) {
            super(name, "Intern");
            this.stipend = stipend;
        }

        @Override
        long pay() {
            return stipend;
        }
    }

    public static void main(String[] args) {
        List<StaffMember> staff = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\s+");
                switch (parts[0]) {
                    case "FullTime":
                        requireLength(parts, 3);
                        staff.add(new FullTime(parts[1], nonNegative(parts[2])));
                        break;
                    case "PartTime":
                        requireLength(parts, 4);
                        staff.add(new PartTime(parts[1], nonNegative(parts[2]), nonNegative(parts[3])));
                        break;
                    case "Intern":
                        requireLength(parts, 3);
                        staff.add(new Intern(parts[1], nonNegative(parts[2])));
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown staff type: " + parts[0]);
                }
            }
        }

        if (staff.isEmpty()) {
            return;
        }

        StaffMember[] payslips = staff.toArray(new StaffMember[0]);
        long total = 0;
        StaffMember topEarner = payslips[0];
        for (StaffMember payslip : payslips) {
            System.out.println(payslip);
            total = Math.addExact(total, payslip.pay());
            if (payslip.pay() > topEarner.pay()) {
                topEarner = payslip;
            }
        }
        System.out.println("Total " + total);
        System.out.println("top earner " + topEarner.name);
    }

    private static long nonNegative(String value) {
        long amount = Long.parseLong(value);
        if (amount < 0) {
            throw new IllegalArgumentException("Pay amounts, hours, and rates must be non-negative.");
        }
        return amount;
    }

    private static void requireLength(String[] parts, int expectedLength) {
        if (parts.length != expectedLength) {
            throw new IllegalArgumentException("Invalid staff operation format.");
        }
    }
}
