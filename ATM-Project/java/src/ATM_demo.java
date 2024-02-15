import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import Transactions.*;
import FileManager.*;
import Transactions.AdvancedFeatures.*;
import Transactions.AdvancedFeatures.SecurityManager;

import static Transactions.AdvancedFeatures.CurrencyConverter.readAccountBalanceFromFile;

public class ATM_demo {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            FileManager fileManager = FileManager.getInstance();
            List<String> userRecords = fileManager.readUserRecords();

            // ATM operations
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            // Authentication
            boolean isAuthenticated = authenticateUser(username, userRecords);
            if (!isAuthenticated) {
                System.out.println("Authentication failed. Exiting...");
                return;
            }

            displayBalance(username, userRecords);

            boolean exit = false;
            while (!exit) {
                displayMenu();
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        Transaction depositTransaction = TransactionFactory.createTransaction("DEPOSIT", username, depositAmount);
                        depositTransaction.execute();
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        Transaction withdrawTransaction = TransactionFactory.createTransaction("WITHDRAW", username, withdrawAmount);
                        withdrawTransaction.execute();
                        break;
                    case 3:
                        System.out.print("Enter receiver's username: ");
                        String receiverUsername = scanner.next();
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        Transaction transferTransaction = TransactionFactory.createTransferTransaction("TRANSFER", username, receiverUsername, transferAmount);
                        transferTransaction.execute();
                        break;
                    case 4:
                        System.out.print("Enter new PIN: ");
                        String newPin = scanner.next();
                        Transaction changePinTransaction = TransactionFactory.createChangePinTransaction("CHANGE_PIN", username, newPin);
                        changePinTransaction.execute();
                        break;

                    case 5:
                        // Display Transaction History
                        displayTransactionHistory();
                        break;
                    case 6:
                        // Display Scheduled Transactions
                        displayScheduledTransactions();
                        break;
                    case 7:
                        // Display Account Alerts
                        displayAccountAlerts();
                        break;
                    case 8:
                        // Display Multi-Currency Support
                        displayMultiCurrencySupport();
                        break;
                    case 9:
                        // Display User Profile Management
                        displayUserProfileManagement();
                        break;
                    case 10:
                        // Display Security Enhancements
                        displaySecurityEnhancements();
                        break;

                    case 11:
                        System.out.println("Exiting...");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading/writing user records file: " + e.getMessage());
        }
    }

    private static boolean authenticateUser(String username, List<String> userRecords) {
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (String record : userRecords) {
            String[] parts = record.split(",");
            if (parts[0].equals(username) && parts[1].equals(password)) {
                return true;
            }
        }
        return false;
    }

    private static void displayMenu() {
        // Display menu
        System.out.println("Menu:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. Change PIN");
        System.out.println("5. Display Transaction History");
        System.out.println("6. Display Scheduled Transactions");
        System.out.println("7. Display Account Alerts");
        System.out.println("8. Display Multi-Currency Support");
        System.out.println("9. User Profile Management");
        System.out.println("10. Display Security Enhancements");
        System.out.println("11. EXIT");
        System.out.print("Enter your choice: ");
    }

    private static void displayBalance(String username, List<String> userRecords) {
        for (String record : userRecords) {
            String[] parts = record.split(",");
            if (parts[0].equals(username)) {
                System.out.println("Your current balance is: " + parts[2]);
                break;
            }
        }
    }


    //OTHER METHODS

    // Method to display Transaction History
    private static void displayTransactionHistory() {
        // Retrieve and display transaction history
        List<Transaction> transactionHistory = TransactionHistoryManager.getTransactionHistory();
        if (transactionHistory.isEmpty()) {
            System.out.println("Transaction history is empty.");
        } else {
            System.out.println("Transaction History:");
            for (Transaction transaction : transactionHistory) {
                System.out.println(transaction.toString());
            }
        }
    }

    // Method to display Scheduled Transactions
    private static void displayScheduledTransactions() {
        // Retrieve and display scheduled transactions
        List<ScheduledTransaction> scheduledTransactions = ScheduledTransactionManager.getScheduledTransactions();
        if (scheduledTransactions.isEmpty()) {
            System.out.println("No scheduled transactions.");
        } else {
            System.out.println("Scheduled Transactions:");
            for (ScheduledTransaction transaction : scheduledTransactions) {
                System.out.println(transaction.toString());
            }
        }
    }

    // Method to display Account Alerts
    private static void displayAccountAlerts() {
        // Check for unusual activity and send alerts if necessary
        String username = "exampleUser"; // Provide the username here
        AccountAlertManager.checkUnusualActivity(username);
    }

    // Method to display Multi-Currency Support

    private static void displayMultiCurrencySupport() {
        // Read account balance from file or any other data source
        double accountBalance = readAccountBalanceFromFile(); // Implement this method to read the balance from the file

        // Convert currency and display the result
        CurrencyConverter currencyConverter = new CurrencyConverter();
        double convertedAmount = currencyConverter.convertCurrency(accountBalance, "USD", "EUR");
        System.out.println("Converted amount: " + convertedAmount);
    }

    // Method to display User Profile Management
    private static void displayUserProfileManagement() {
        // Retrieve and display user profile
        String name = "exampleUser"; // Provide the username here
        UserProfile userProfile = UserProfileManager.getUserProfile(name);
        if (userProfile != null) {
            System.out.println("User Profile:");
            System.out.println("Username: " + userProfile.getName());
            System.out.println("Email: " + userProfile.getEmail());
            System.out.println("Date of Birth: " + userProfile.getDateOfBirth());
            // Display other profile attributes as needed
        } else {
            System.out.println("User profile not found.");
        }
    }

    // Method to display Security Enhancements
    private static void displaySecurityEnhancements() {
        // Perform security enhancements
        SecurityManager securityManager = new SecurityManager();
        String encryptedData = securityManager.encryptData("Sensitive information");
        System.out.println("Encrypted Data: " + encryptedData);
        String decryptedData = securityManager.decryptData(encryptedData);
        System.out.println("Decrypted Data: " + decryptedData);
    }


}
