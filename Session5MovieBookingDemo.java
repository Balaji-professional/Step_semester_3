import cinehub.loyalty.PremiumMovieTicket;
import cinehub.tickets.MovieTicket;
import java.util.Arrays;

public class Session5MovieBookingDemo {
    public static void main(String[] args) {
        System.out.println(AccessChecker.classifyAccess("private", "SAME_CLASS"));
        System.out.println(AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE"));
        System.out.println(AccessChecker.classifyAccess(
                "protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println(AccessChecker.classifyAccess(
                "protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
        System.out.println(AccessChecker.summarizeBatch(new String[][]{
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"public", "DIFFERENT_PACKAGE"}}));

        MovieTicket baseTicket = new MovieTicket("A1", "SCREEN-1", 12.50, "CineHub Premiere");
        PremiumMovieTicket premium = new PremiumMovieTicket("B1", "SCREEN-2", 20.00, "CineHub Premiere");
        System.out.println("Private seat through getter: " + baseTicket.getSeatNumber());
        System.out.println("Premium subclass protected price: " + premium.readTicketPriceThroughOwnType());

        CineScreen screen = new CineScreen(2);
        screen.bookSeat();
        screen.bookSeat();
        screen.bookSeat();
        System.out.println("Seats after extra booking: " + screen.getSeatsAvailable());
        screen.cancelBooking();
        screen.cancelBooking();
        screen.cancelBooking();
        System.out.println("Seats after extra cancellation: " + screen.getSeatsAvailable());

        MovieBookingProfile profile = new MovieBookingProfile("Rahul Dev");
        profile.setConfirmed(true);
        profile.setOtp("4471");
        System.out.println(profile.getName() + " confirmed: " + profile.isConfirmed());

        String[] originalSeats = {"A1", "A2"};
        BookingReceipt receipt = new BookingReceipt("CH-1001", originalSeats);
        originalSeats[0] = "X";
        String[] copiedSeats = receipt.getSeatNumbers();
        copiedSeats[0] = "Y";
        BookingReceipt updated = receipt.withUpdatedSeat(1, "A3");
        System.out.println("Original seats: " + Arrays.toString(receipt.getSeatNumbers()));
        System.out.println("Updated seats: " + Arrays.toString(updated.getSeatNumbers()));
        System.out.println(BookingReceipt.processNightlySettlement(new BookingReceipt[]{
                new GroupBookingReceipt("CH-2002", new String[]{"B1", "B2"}, 2),
                null,
                new BookingReceipt("CH-3003", new String[]{"C1"})}));
    }
}