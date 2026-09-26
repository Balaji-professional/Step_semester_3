package cinehub.tickets;

public class MovieTicket {
    private final String seatNumber;
    final String screenId;
    protected final double ticketPrice;
    public final String movieTitle;

    public MovieTicket(String seatNumber, String screenId, double ticketPrice, String movieTitle) {
        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
}