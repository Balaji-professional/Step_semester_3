# Step_semester_3

## Category B Practice Problems

Compile all examples with `javac *.java` and run the sample demonstrations with `java CategoryBPracticeProblems`.

- **Talking Toy Box:** `Toy` is abstract, assigns each instance a unique ID, and is directly extended by `ToyCar` and `ToyRobot`.
- **Warehouse Label Printer:** `PackageBox` and `Invoice` independently implement `Printable`; `WarehouseLabelPrinter.printAll` handles both through the interface.
- **Orchestra Warm-Up:** `Violin` extends `StringInstrument`, which extends `Instrument`. Java does not permit calling `super.play()` when `Instrument.play()` is abstract. Therefore `StringInstrument` supplies its own concrete implementation, and `Violin` calls that implementation using `super.play()` before adding its detail.
- **Smart Kitchen Assistant:** `KitchenTool` validates speed levels before assignment; `Blender` extends it and independently implements `Washable`.
- **Package Drop-Off Log:** `ParcelNote` and `LetterNote` extend `DeliveryNote`; the signed overload calls the polymorphic no-argument method.
