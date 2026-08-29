import java.util.Scanner;

public class SrmStudent {
    static String collegeName;
    static int academicYear;

    static {
        collegeName = "SRM";
        academicYear = 2026;
        System.out.println("College info loaded");
    }

    SrmStudent(String name) {
        System.out.println("Student record created: " + name);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter name: ");
            String name = sc.next();
            new SrmStudent(name);
        }

        sc.close();
    }
}