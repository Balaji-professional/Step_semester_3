import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem1BankAccountWithdrawal {
    private abstract static class Account {
        private final String id;
        private long balance;

        private Account(String id, long initialBalance) {
            if (initialBalance <= 0) {
                throw new IllegalArgumentException("Initial balance must be positive.");
            }
            this.id = id;
            this.balance = initialBalance;
        }

        protected final long balance() {
            return balance;
        }

        abstract String withdrawalRejection(long amount);

        private String withdraw(long amount) {
            if (amount <= 0) {
                return id + " rejected: amount must be positive";
            }
            String rejection = withdrawalRejection(amount);
            if (rejection != null) {
                return id + " rejected: " + rejection;
            }
            balance -= amount;
            return id + " balance " + balance;
        }
    }

    private static final class SavingsAccount extends Account {
        private SavingsAccount(String id, long initialBalance) {
            super(id, initialBalance);
        }

        @Override
        String withdrawalRejection(long amount) {
            return balance() - amount < 1000 ? "minimum balance 1000" : null;
        }
    }

    private static final class CurrentAccount extends Account {
        private CurrentAccount(String id, long initialBalance) {
            super(id, initialBalance);
        }

        @Override
        String withdrawalRejection(long amount) {
            return balance() - amount < -5000 ? "overdraft limit 5000" : null;
        }
    }

    public static void main(String[] args) {
        Map<String, Account> accounts = new HashMap<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\s+");
                switch (parts[0]) {
                    case "Savings":
                    case "Current":
                        if (parts.length != 3) {
                            throw new IllegalArgumentException("Account format: <Type> <ID> <InitialBalance>");
                        }
                        long initialBalance = Long.parseLong(parts[2]);
                        Account account = parts[0].equals("Savings")
                            ? new SavingsAccount(parts[1], initialBalance)
                            : new CurrentAccount(parts[1], initialBalance);
                        accounts.put(parts[1], account);
                        break;
                    case "WITHDRAW":
                        if (parts.length != 3) {
                            throw new IllegalArgumentException("Withdrawal format: WITHDRAW <ID> <Amount>");
                        }
                        Account existing = accounts.get(parts[1]);
                        if (existing == null) {
                            System.out.println("Account not found");
                        } else {
                            System.out.println(existing.withdraw(Long.parseLong(parts[2])));
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operation: " + parts[0]);
                }
            }
        }
    }
}
