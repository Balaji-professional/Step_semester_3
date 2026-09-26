public class CategoryBPracticeProblems {
    public static void main(String[] args) {
        ToyCar car = new ToyCar("Speedster");
        ToyRobot robot = new ToyRobot("Bolt");
        System.out.println(car.makeSound());
        System.out.println(robot.makeSound());
        System.out.println(car.getToyId());
        System.out.println(robot.getToyId());

        PackageBox box = new PackageBox("TRK-88");
        Invoice invoice = new Invoice("INV-42");
        WarehouseLabelPrinter.printAll(new Printable[]{box, invoice});

        StringInstrument strings = new StringInstrument();
        Violin violin = new Violin();
        System.out.println(strings.play());
        System.out.println(violin.play());

        Blender blender = new Blender();
        blender.setSpeedLevel(3);
        System.out.println(blender.getSpeedLevel());
        try {
            blender.setSpeedLevel(9);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage() + " Current level: " + blender.getSpeedLevel());
        }
        System.out.println(blender.prepare());
        System.out.println(blender.clean());

        ParcelNote parcel = new ParcelNote("TRK-1");
        DeliveryNote parcelReference = parcel;
        System.out.println(parcel.confirmDelivery());
        System.out.println(parcel.confirmDelivery("J. Smith"));
        PackageDropOffLog.logAll(new DeliveryNote[]{parcelReference, new LetterNote("TRK-2")});
    }
}