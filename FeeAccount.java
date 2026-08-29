import java.util.Scanner;

public class FeeAccount {

    void pay(double amount) {
        System.out.println("Paid in one go (day-scholar account)");
    }

    static class HostelFeeAccount extends FeeAccount {
        void pay(double amount) {
            System.out.println("Paid in two installments (hostel account)");
        }
    }

    static void processPayment(FeeAccount account, double amount) {
        if (account instanceof HostelFeeAccount)
            ((HostelFeeAccount) account).pay(amount);
        else
            account.pay(amount);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of accounts: ");
        int n = sc.nextInt();

        FeeAccount[] accounts = new FeeAccount[n];
        int hostel = 0, dayScholar = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("Enter 1 for Hostel, 2 for Day-scholar: ");
            int type = sc.nextInt();

            if (type == 1) {
                accounts[i] = new HostelFeeAccount();
                hostel++;
            } else {
                accounts[i] = new FeeAccount();
                dayScholar++;
            }
        }

        System.out.print("Enter payment amount: ");
        double amount = sc.nextDouble();

        for (FeeAccount a : accounts)
            processPayment(a, amount);

        System.out.println("Hostel accounts processed: " + hostel +
                " | Day-scholar accounts processed: " + dayScholar);

        sc.close();
    }
}