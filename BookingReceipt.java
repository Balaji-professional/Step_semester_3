import java.util.Arrays;
import java.util.Objects;

public sealed class BookingReceipt permits GroupBookingReceipt {
    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        if (bookingId == null || bookingId.isBlank()) {
            throw new IllegalArgumentException("Booking ID cannot be blank.");
        }
        Objects.requireNonNull(seatNumbers, "Seat numbers cannot be null.");
        if (seatNumbers.length > 10) {
            throw new IllegalArgumentException("A receipt can contain at most 10 seats.");
        }
        this.bookingId = bookingId;
        this.seatNumbers = Arrays.copyOf(seatNumbers, seatNumbers.length);
    }

    public String getBookingId() {
        return bookingId;
    }

    public String[] getSeatNumbers() {
        return Arrays.copyOf(seatNumbers, seatNumbers.length);
    }

    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        if (index < 0 || index >= seatNumbers.length) {
            throw new IndexOutOfBoundsException("Seat index is outside this receipt.");
        }
        if (newSeat == null || newSeat.isBlank()) {
            throw new IllegalArgumentException("Seat number cannot be blank.");
        }
        String[] updatedSeats = getSeatNumbers();
        updatedSeats[index] = newSeat;
        return new BookingReceipt(bookingId, updatedSeats);
    }

    public static String processNightlySettlement(BookingReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;
        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 group | 0 individual";
        }
        for (BookingReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (receipt instanceof GroupBookingReceipt) {
                group++;
            } else {
                individual++;
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + group
                + " group | " + individual + " individual";
    }
}