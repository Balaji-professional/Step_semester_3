import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class HotelBookingSystem {
    private static final class Customer {
        private final String name;

        private Customer(String name) {
            this.name = name;
        }
    }

    private abstract static class Room {
        private final String id;

        private Room(String id) {
            this.id = id;
        }

        abstract double calculatePrice(long nights);
    }

    private static final class StandardRoom extends Room {
        private StandardRoom(String id) {
            super(id);
        }

        @Override
        double calculatePrice(long nights) {
            return nights * 100.0;
        }
    }

    private static final class DeluxeRoom extends Room {
        private DeluxeRoom(String id) {
            super(id);
        }

        @Override
        double calculatePrice(long nights) {
            return nights * 175.0;
        }
    }

    private static final class Suite extends Room {
        private Suite(String id) {
            super(id);
        }

        @Override
        double calculatePrice(long nights) {
            return nights * 300.0;
        }
    }

    private static final class Reservation {
        private final Customer customer;
        private final Room room;
        private final LocalDate checkIn;
        private final LocalDate checkOut;
        private final LocalDateTime cancellationDeadline;
        private boolean active = true;

        private Reservation(Customer customer, Room room, LocalDate checkIn, LocalDate checkOut,
                            LocalDateTime cancellationDeadline) {
            this.customer = customer;
            this.room = room;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.cancellationDeadline = cancellationDeadline;
        }
    }

    private static final class BookingService {
        private final List<Reservation> reservations = new ArrayList<>();

        private boolean isAvailable(Room room, LocalDate start, LocalDate end) {
            if (start == null || end == null || !end.isAfter(start)) {
                return false;
            }
            return reservations.stream().noneMatch(reservation -> reservation.active
                    && reservation.room.id.equals(room.id)
                    && start.isBefore(reservation.checkOut)
                    && end.isAfter(reservation.checkIn));
        }

        private Reservation reserve(Customer customer, Room room, LocalDate start, LocalDate end,
                                    LocalDateTime cancellationDeadline) {
            if (!isAvailable(room, start, end)) {
                System.out.println(room.id + " is not available from " + start + " to " + end + ".");
                return null;
            }
            Reservation reservation = new Reservation(customer, room, start, end, cancellationDeadline);
            reservations.add(reservation);
            long nights = ChronoUnit.DAYS.between(start, end);
            System.out.println("Reservation confirmed for " + customer.name + ", " + room.id + " (" + start
                    + " to " + end + ").");
            System.out.printf("Price: $%.2f%n", room.calculatePrice(nights));
            return reservation;
        }

        private void cancel(Reservation reservation, LocalDateTime requestedAt) {
            if (reservation == null || !reservation.active) {
                System.out.println("Reservation is not active.");
                return;
            }
            if (!requestedAt.isBefore(reservation.cancellationDeadline)) {
                System.out.println("Cancellation deadline has passed.");
                return;
            }
            reservation.active = false;
            System.out.println("Reservation for " + reservation.customer.name + ", " + reservation.room.id
                    + " (" + reservation.checkIn + " to " + reservation.checkOut + ") cancelled successfully.");
        }
    }

    public static void main(String[] args) {
        BookingService service = new BookingService();
        Customer customerA = new Customer("Customer A");
        Customer customerB = new Customer("Customer B");
        Customer customerC = new Customer("Customer C");
        Room standard = new StandardRoom("Standard Room 101");
        Room deluxe = new DeluxeRoom("Deluxe Room 201");
        LocalDate jan1 = LocalDate.of(2027, 1, 1);
        LocalDate jan5 = LocalDate.of(2027, 1, 5);

        System.out.println(standard.id + (service.isAvailable(standard, jan1, jan5)
                ? " is available from " : " is not available from ") + jan1 + " to " + jan5 + ".");
        Reservation reservation = service.reserve(customerA, standard, jan1, jan5,
                LocalDateTime.of(2026, 12, 20, 23, 59));
        service.reserve(customerB, standard, LocalDate.of(2027, 1, 3), LocalDate.of(2027, 1, 7),
                LocalDateTime.of(2026, 12, 20, 23, 59));
        service.cancel(reservation, LocalDateTime.of(2026, 12, 19, 12, 0));
        service.reserve(customerC, deluxe, LocalDate.of(2027, 2, 10), LocalDate.of(2027, 2, 12),
                LocalDateTime.of(2027, 1, 31, 23, 59));
    }
}