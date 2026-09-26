import java.time.LocalDate;

public class EmployeeLeaveWorkflow {
    private interface LeavePolicy {
        boolean allows(int requestedDays);
    }

    private static final class MaximumDaysPolicy implements LeavePolicy {
        private final int maximumDays;

        private MaximumDaysPolicy(int maximumDays) {
            this.maximumDays = maximumDays;
        }

        @Override
        public boolean allows(int requestedDays) {
            return requestedDays > 0 && requestedDays <= maximumDays;
        }
    }

    private abstract static class Employee {
        private final String name;
        private final LeavePolicy leavePolicy;

        private Employee(String name, LeavePolicy leavePolicy) {
            this.name = name;
            this.leavePolicy = leavePolicy;
        }

        private boolean canRequestLeave(LocalDate start, LocalDate end) {
            if (start == null || end == null || end.isBefore(start)) {
                return false;
            }
            int days = Math.toIntExact(end.toEpochDay() - start.toEpochDay() + 1);
            return leavePolicy.allows(days);
        }
    }

    private static final class FullTimeEmployee extends Employee {
        private FullTimeEmployee(String name) {
            super(name, new MaximumDaysPolicy(30));
        }
    }

    private static final class PartTimeEmployee extends Employee {
        private PartTimeEmployee(String name) {
            super(name, new MaximumDaysPolicy(14));
        }
    }

    private static final class Contractor extends Employee {
        private Contractor(String name) {
            super(name, new MaximumDaysPolicy(0));
        }
    }

    private enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }

    private static final class LeaveRequest {
        private final Employee employee;
        private final LocalDate start;
        private final LocalDate end;
        private Status status = Status.PENDING;

        private LeaveRequest(Employee employee, LocalDate start, LocalDate end) {
            this.employee = employee;
            this.start = start;
            this.end = end;
        }

        private void review(Status decision) {
            if (status != Status.PENDING || (decision != Status.APPROVED && decision != Status.REJECTED)) {
                throw new IllegalStateException("Cannot change leave request status from " + display(status)
                        + " to " + display(decision) + ".");
            }
            status = decision;
        }

        private static String display(Status status) {
            String value = status.name().toLowerCase();
            return Character.toUpperCase(value.charAt(0)) + value.substring(1);
        }
    }

    private static final class Reviewer {
        private final String name;

        private Reviewer(String name) {
            this.name = name;
        }

        private void review(LeaveRequest request, Status decision) {
            request.review(decision);
            String action = decision == Status.APPROVED ? "approved" : "rejected";
            System.out.println(request.employee.name + "'s leave request (" + request.start + " to " + request.end
                    + ") " + action + " by " + name + ". Status: " + LeaveRequest.display(request.status) + ".");
        }
    }

    private static LeaveRequest submit(Employee employee, LocalDate start, LocalDate end) {
        if (!employee.canRequestLeave(start, end)) {
            throw new IllegalArgumentException("Leave dates do not meet the employee's leave policy.");
        }
        LeaveRequest request = new LeaveRequest(employee, start, end);
        System.out.println("Leave request submitted for " + employee.name + " (" + start + " to " + end
                + "). Status: Pending.");
        return request;
    }

    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");
        Reviewer alice = new Reviewer("Alice");
        Reviewer bob = new Reviewer("Bob");

        LeaveRequest johnRequest = submit(john, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 5));
        alice.review(johnRequest, Status.APPROVED);
        LeaveRequest janeRequest = submit(jane, LocalDate.of(2026, 2, 10), LocalDate.of(2026, 2, 11));
        bob.review(janeRequest, Status.REJECTED);
        try {
            johnRequest.review(Status.PENDING);
        } catch (IllegalStateException exception) {
            System.out.println(exception.getMessage());
        }
    }
}