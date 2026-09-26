public abstract class DeliveryNote {
    private final String trackingId;

    protected DeliveryNote(String trackingId) {
        if (trackingId == null || trackingId.isBlank()) {
            throw new IllegalArgumentException("Tracking ID cannot be blank.");
        }
        this.trackingId = trackingId;
    }

    public abstract String confirmDelivery();

    public final String confirmDelivery(String signature) {
        if (signature == null || signature.isBlank()) {
            throw new IllegalArgumentException("Signature cannot be blank.");
        }
        return confirmDelivery() + ", signed by " + signature.trim();
    }

    protected final String getTrackingId() {
        return trackingId;
    }
}