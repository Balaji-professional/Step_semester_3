package cinehub.loyalty;

import cinehub.tickets.MovieTicket;

public class PremiumMovieTicket extends MovieTicket {
    public PremiumMovieTicket(String seatNumber, String screenId, double ticketPrice, String movieTitle) {
        super(seatNumber, screenId, ticketPrice, movieTitle);
    }

    public double readTicketPriceThroughOwnType() {
        return ticketPrice;
    }
}