# Step_semester_3

## Session 5: Movie Booking and Access Control

Compile with `javac -d out *.java` and run `java -cp out Session5MovieBookingDemo`.

The examples cover Java access rules, seat-count encapsulation, JavaBean properties, and immutable booking receipts. `BookingReceipt` is sealed rather than final because the required `GroupBookingReceipt` subtype must be stored in a `BookingReceipt[]` and recognized with `instanceof`; those requirements cannot both be met if the base class is final. The protected parent-reference case is denied according to Java's cross-package access rule.