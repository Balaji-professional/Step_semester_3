public final class WarehouseLabelPrinter {
    private WarehouseLabelPrinter() {
    }

    public static void printAll(Printable[] items) {
        if (items == null) {
            throw new IllegalArgumentException("Items cannot be null.");
        }
        for (Printable item : items) {
            if (item == null) {
                throw new IllegalArgumentException("Items cannot contain null.");
            }
            System.out.println(item.printLabel());
        }
    }
}