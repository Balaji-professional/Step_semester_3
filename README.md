# Step_semester_3

## OOP Coding Questions

Each Java file below is a standalone demonstration. Compile all examples with `javac *.java`, then run an example with `java ClassName`.

- `VehicleRentalSystem`: vehicle subclasses calculate category-specific prices; active rentals control vehicle availability.
- `EmployeeLeaveWorkflow`: employee leave policies validate requests; a reviewer can move a request only from Pending to Approved or Rejected.
- `OnlineExaminationSystem`: question subclasses evaluate answers polymorphically; submitted attempts lock answers and report scores.
- `HotelBookingSystem`: room subclasses calculate prices; reservations prevent overlapping dates and enforce a cancellation deadline.
- `ShoppingPaymentSystem`: payment methods process orders polymorphically; an order becomes Paid only after successful payment.

### Relationship Summary

- **Vehicle rental:** Sedan and SUV generalize Vehicle. Each Rental has exactly one Customer and one Vehicle; each Customer and Vehicle can be associated with `0..*` rentals over time. A Vehicle can have at most one active Rental.
- **Leave workflow:** Full-time, part-time, and contractor employees generalize Employee. Each LeaveRequest belongs to exactly one Employee; an Employee can have `0..*` requests. Each request is handled by a Reviewer before its status changes.
- **Examination:** Question types generalize Question. An Examination contains `1..*` Questions; a Student can have `0..*` Attempts, and each Attempt refers to exactly one Student and one Examination. An Attempt records `0..*` answers, at most one per question.
- **Hotel booking:** Room categories generalize Room. A Reservation refers to exactly one Customer and one Room; each can be associated with `0..*` reservations over time. Active reservations for the same room cannot overlap.
- **Shopping payment:** A Customer can place `0..*` Orders, and each Order belongs to one Customer. An Order contains `0..*` items while being prepared and must contain at least one before payment. Payment methods implement a common interface used by the payment service.

## Quiz Answers

1. C, Polymorphism.
2. C, Encapsulation.
3. C, One Author has `1..*` Books; each Book has exactly `1` Author.
4. D, Composition.
5. D, Generalization.
6. C, Realization.
7. B, Association.
8. C, State diagram.
9. B, Abstraction.
10. C, Sequence diagram.

## Concept Answers

1. **Encapsulation:** `User` should own and protect its email state. Direct writes could bypass format and uniqueness checks, leaving the object invalid. A controlled `changeEmail` operation validates both rules before changing the private field, so every accepted state remains valid.

2. **Inheritance and composition:** Inheritance is appropriate for genuine `is-a` report categories with shared behavior. Optional export and visualization features fit composition better: a report can receive the capabilities it needs through interfaces or collaborators. Forcing every capability into one inheritance tree creates rigid combinations and makes new feature combinations harder to add.

3. **Abstraction and polymorphism:** A `Notification` interface declares a common send operation. Email, SMS, and push implementations override it with their own behavior. `NotificationService` calls the interface method, and runtime dispatch selects the implementation. Adding `InAppNotification` requires a new implementation, not type-specific branches in the service.

4. **Composition and aggregation:** Organization to Department is composition because the departments' lifecycle depends on the organization in this model. Department to Employee is aggregation because employees can outlive a department and move elsewhere. Employees remain independently identifiable and are not destroyed when a department is dissolved.

5. **Multiplicity and uniqueness:** Student and Course form a many-to-many relationship: a Student may take `0..*` Courses, and a Course may have `0..*` Students. Multiplicity describes the number of related objects, not uniqueness of a particular enrollment pair. Preventing duplicate student-course enrollments requires a constraint in application logic or a unique key on the enrollment relation.