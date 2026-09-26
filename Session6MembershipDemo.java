public class Session6MembershipDemo {
    public static void main(String[] args) {
        try {
            new LibraryMember("LB1", 3);
        } catch (IllegalArgumentException exception) {
            System.out.println("LB1 rejected: " + exception.getMessage());
        }

        StudentMember student = new StudentMember("STU10", 3, "CSE");
        student.borrowBook();
        student.borrowBook();
        System.out.println("Student books borrowed: " + student.getBooksBorrowed());
        System.out.println(LibraryMember.enrollBatch(
                new String[]{"STU1", "LB1", "STU2", " ", "STU3"}, 3));

        HonorsStudentMember honors = new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember faculty = new FacultyMember("FAC4", 5, "Physics");
        honors.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();
        System.out.println(new LibraryMember("GEN5", 3).displayInfo());
        System.out.println(student.displayInfo());
        System.out.println(honors.displayInfo());
        System.out.println(faculty.displayInfo());
        System.out.println(LibraryMember.classifyGeneration(honors));
        System.out.println(LibraryMember.classifyGeneration(faculty));
        System.out.println("Mixed borrowed total: " + LibraryMember.getTotalBooksBorrowed(
                new LibraryMember[]{student, honors, faculty}));

        student.chargeFine(100);
        int[] fineHistory = student.getFineHistory();
        fineHistory[0] = 999;
        System.out.println("Fine total/history: " + student.getTotalFine() + "/"
                + student.getFineHistory()[0]);
        System.out.println(LibraryMember.batchPrint(
                new LibraryMember[]{new LibraryMember("LB05", 3), new StudentMember("STU6", 3, "ECE")}));

        LibraryMember numbered = new LibraryMember(3);
        System.out.println("Member number: " + numbered.memberNumber);
        System.out.println("Renewal codes: " + LibraryMember.isValidRenewalCode("R12A") + ", "
                + LibraryMember.isValidRenewalCode("R1A") + ", "
                + LibraryMember.isValidRenewalCode("X12A"));
        numbered.borrowBook();
        numbered.borrowBook("Fiction");
        System.out.println("Numbered member books: " + numbered.getBooksBorrowed());
        System.out.println(LibraryMember.processNightlyAudit(
                new LibraryMember[]{new FacultyMember(5, "Physics"), null, new LibraryMember(3)}));
        System.out.println("Members enrolled: " + LibraryMember.getMembersEnrolled());
    }
}