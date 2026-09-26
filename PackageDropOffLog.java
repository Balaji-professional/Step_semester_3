public final class PackageDropOffLog {
    private PackageDropOffLog() {
    }

    public static void logAll(DeliveryNote[] notes) {
        if (notes == null) {
            throw new IllegalArgumentException("Notes cannot be null.");
        }
        for (DeliveryNote note : notes) {
            if (note == null) {
                throw new IllegalArgumentException("Notes cannot contain null.");
            }
            System.out.println(note.confirmDelivery());
        }
    }
}