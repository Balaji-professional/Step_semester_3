public class LetterNote extends DeliveryNote {
    public LetterNote(String trackingId) {
        super(trackingId);
    }

    @Override
    public String confirmDelivery() {
        return "Letter " + getTrackingId() + " delivered";
    }
}