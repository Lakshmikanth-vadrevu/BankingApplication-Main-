import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankingApplication {
    private Map<String, Account> accounts;
    private Scanner scanner;
    private Map<String, User> users;

    public BankingApplication() {
        accounts = new HashMap<>();
        scanner = new Scanner(System.in);
        users = new HashMap<>();
    }

    public void start() {
        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Transaction History");
            System.out.println("6. Login");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    transactionHistory();
                    break;
                case 6:
                    login();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void createAccount() {
        System.out.println("Enter username:");
        String username = scanner.next();

        System.out.println("Enter password:");
        String password = scanner.next();

        System.out.println("Enter account number:");
        String accountNumber = scanner.next();

        System.out.println("Enter account holder name:");
        String accountHolderName = scanner.next();

        System.out.println("Enter initial balance:");
        double initialBalance = scanner.nextDouble();

        System.out.println("Enter account type (Savings/Current):");
        String accountType = scanner.next();

        Account account = new Account(accountNumber, accountHolderName, initialBalance, accountType);
        accounts.put(accountNumber, account);

        User user = new User(username, password, accountNumber);
        users.put(username, user);

        System.out.println("Account created successfully");
    }

    private void deposit() {
        System.out.println("Enter account number:");
        String accountNumber = scanner.next();

        System.out.println("Enter amount to deposit:");
        double amount = scanner.nextDouble();

        Account account = accounts.get(accountNumber);

        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposit successful");
        } else {
            System.out.println("Account not found");
        }
    }

    private void withdraw() {
        System.out.println("Enter account number:");
        String accountNumber = scanner.next();

        System.out.println("Enter amount to withdraw:");
        double amount = scanner.nextDouble();

        Account account = accounts.get(accountNumber);

        if (account != null) {
            if (account.getBalance() >= amount) {
                account.withdraw(amount);
                System.out.println("Withdrawal successful");
            } else {
                System.out.println("Insufficient funds");
            }
        } else {
            System.out.println("Account not found");
        }
    }

    private void checkBalance() {
        System.out.println("Enter account number:");
        String accountNumber = scanner.next();

        Account account = accounts.get(accountNumber);

        if (account != null) {
            System.out.println("Account balance: " + account.getBalance());
        } else {
            System.out.println("Account not found");
        }
    }

    private void transactionHistory() {
        System.out.println("Enter account number:");
        String accountNumber = scanner.next();

        Account account = accounts.get(accountNumber);

        if (account != null) {
            System.out.println("Transaction History:");
            for (Transaction transaction : account.getTransactions()) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("Account not found");
        }
    }

    private void login() {
        System.out.println("Enter username:");
        String username = scanner.next();

        System.out.println("Enter password:");
        String password = scanner.next();

        if (users.containsKey(username)) {
            User user = users.get(username);
            if (user.getPassword().equals(password)) {
                System.out.println("Login successful");
                // Add code here to allow logged-in user to access account features
            } else {
                System.out.println("Invalid password");
            }
        } else {
            System.out.println("Username not found");
        }
    }

    public static void main(String[] args) {
        BankingApplication app = new BankingApplication();
        app.start();
    }
}
