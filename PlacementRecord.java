import java.util.Scanner;

class PlacementRecord {

    String studentName;
    String company;
    double packageLpa;

    PlacementRecord(String studentName, String company, double packageLpa) {
        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }

    void printRecord() {
        System.out.println(studentName + " -> " + company + " @ " + packageLpa + " LPA");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PlacementRecord[] records = new PlacementRecord[3];

        for (int i = 0; i < 3; i++) {

            System.out.println("Enter details of student " + (i + 1));

            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            System.out.print("Enter company: ");
            String company = sc.nextLine();

            System.out.print("Enter package LPA: ");
            double packageLpa = sc.nextDouble();
            sc.nextLine();

            records[i] = new PlacementRecord(name, company, packageLpa);
        }

        System.out.println("\nPlacement Records:");

        for (int i = 0; i < records.length; i++) {
            records[i].printRecord();
        }

        sc.close();
    }
}