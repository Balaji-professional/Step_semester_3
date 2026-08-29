import java.util.Scanner;

public class Account {
    String regNo;
    double totalFee;

    Account(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    final double calculateLateFee(int daysLate) {
        return daysLate * 2200;
    }

    final void printSummary(int daysLate) {
        if (daysLate <= 0)
            System.out.println(regNo + " - On time, no late fee");
        else
            System.out.println(regNo + " | Total Fee: Rs " + totalFee
                    + " | Late Fee: Rs " + calculateLateFee(daysLate));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of accounts: ");
        int n = sc.nextInt();

        Account[] a = new Account[n];
        int[] late = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Reg No: ");
            String r = sc.next();

            System.out.print("Enter Total Fee: ");
            double f = sc.nextDouble();

            System.out.print("Enter Days Late: ");
            late[i] = sc.nextInt();

            a[i] = new Account(r, f);
        }

        for (int i = 0; i < n; i++)
            a[i].printSummary(late[i]);

        sc.close();
    }
}