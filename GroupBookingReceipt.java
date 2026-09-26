public final class GroupBookingReceipt extends BookingReceipt {
    private final int groupSize;

    public GroupBookingReceipt(String bookingId, String[] seatNumbers, int groupSize) {
        super(bookingId, seatNumbers);
        if (groupSize <= 0) {
            throw new IllegalArgumentException("Group size must be positive.");
        }
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}