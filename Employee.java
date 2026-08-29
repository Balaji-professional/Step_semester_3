import java.util.Scanner;

public class Employee {
    String id;
    double salary;

    Employee(String id, double salary) {
        this.id = id;
        this.salary = salary;
    }

    void raiseSalary(double salary) {
        this.salary = this.salary + salary;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();

        Employee[] e = new Employee[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter ID: ");
            String id = sc.next();

            System.out.print("Enter salary: ");
            double salary = sc.nextDouble();

            e[i] = new Employee(id, salary);
        }

        System.out.print("Enter bonus: ");
        double bonus = sc.nextDouble();

        for (int i = 0; i < n; i++) {
            e[i].raiseSalary(bonus);
            System.out.println(e[i].id +
                    " | Final Salary: Rs " + e[i].salary);
        }

        sc.close();
    }
}