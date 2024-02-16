import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import Transactions.*;
import FileManager.*;
import Transactions.AdvancedFeatures.*;
import Transactions.AdvancedFeatures.SecurityManager;

import static Transactions.AdvancedFeatures.CurrencyConverter.readAccountBalanceFromFile;


public class ATM {

    // Initialize scanner object to read user input and its reference cannot be changed once it is initialized.
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // Retrieve FileManager instance
            FileManager fileManager = FileManager.getInstance();
            // Read user records from file
            List<String> userRecords = fileManager.readUserRecords();

            // ATM operations
            // Prompt user to enter username
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            // Authentication
            boolean isAuthenticated = authenticateUser(username, userRecords);
            // Check if authentication failed
            if (!isAuthenticated) {
                System.out.println("Authentication failed. Exiting...");
                return;
            }

            // Display user balance
            displayBalance(username, userRecords);

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
            int choice = scanner.nextInt();

            // Handle user's choice
            switch (choice) {
                case 1:
                    // Deposit operation
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    // Create deposit transaction
                    Transaction depositTransaction = TransactionFactory.createTransaction("DEPOSIT", username, depositAmount);
                    // Execute deposit transaction
                    depositTransaction.execute();
                    break;
                case 2:
                    // Withdraw operation
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    // Create withdraw transaction
                    Transaction withdrawTransaction = TransactionFactory.createTransaction("WITHDRAW", username, withdrawAmount);
                    // Execute withdraw transaction
                    withdrawTransaction.execute();
                    break;
                case 3:
                    // Transfer operation
                    System.out.print("Enter receiver's username: ");
                    String receiverUsername = scanner.next();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    // Create transfer transaction
                    Transaction transferTransaction = TransactionFactory.createTransferTransaction("TRANSFER",username,receiverUsername,transferAmount);
                    // Execute transfer transaction
                    transferTransaction.execute();
                    break;
                case 4:
                    // Change PIN operation
                    System.out.print("Enter new PIN: ");
                    String newPin = scanner.next();
                    // Create change PIN transaction
                    Transaction changePinTransaction = new ChangePinTransaction(username, newPin) {
                        @Override
                        public String getUsername() {
                            return null;
                        }
                    };
                    // Execute change PIN transaction
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
                    // Exit the program
                    System.out.println("Exiting... \nTHANKS FOR VISITING OUR ATM MACHINE!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } catch (IOException e) {
            // Handle IO exception
            System.err.println("Error reading/writing user records file: " + e.getMessage());
        }
    }

    public static boolean authenticateUser(String username, List<String> userRecords) {
        // Prompt user to enter password
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Iterate through user records to check authentication
        for (String record : userRecords) {
            // Split the record into its components using comma as the delimiter
            String[] parts = record.split(",");
            // Check if username and password match any user record
            //parts[0] corresponds to the username of the user, as it's the first element in the parts array (assuming zero-based indexing)
            if (parts[0].equals(username) && parts[1].equals(password)) {
                return true; // Authentication successful
            }
        }
        return false; // Authentication failed
    }

    public static void displayBalance(String username, List<String> userRecords) {
        // Iterate through user records to find user balance
        for (String record : userRecords) {
            // Split the record into its components using comma as the delimiter
            String[] parts = record.split(",");
            // Check if the current record matches the provided username
            if (parts[0].equals(username)) {
                // Display user balance
                //parts[2] corresponds to the balance of the user, as it's the third element in the parts array (assuming zero-based indexing)
                System.out.println("Your current balance is: " + parts[2]);
                break;
            }
        }
    }

    //OTHER METHODS

    // Method to display Transaction History
    public static void displayTransactionHistory() {
        // Retrieve and display transaction history
        List<Transaction> transactionHistory = TransactionHistoryManager.getTransactionHistory();

        if (transactionHistory == null) {
            System.out.println("Error: Transaction history is null"); }
         else if (transactionHistory.isEmpty()) {
            System.out.println("Transaction history is empty.");
        } else {
            System.out.println("Transaction History:");
            for (Transaction transaction : transactionHistory) {
                System.out.println(transaction.toString());
            }
        }
    }

    // Method to display Scheduled Transactions
    static void displayScheduledTransactions() {
        // Retrieve and display scheduled transactions
        List<ScheduledTransaction> scheduledTransactions = ScheduledTransactionManager.getScheduledTransactions();
        if (scheduledTransactions == null) {
            System.out.println("Error: Scheduled Transaction not available!");
        } else if (scheduledTransactions.isEmpty()) {
            System.out.println("No scheduled transactions.");
        } else {
            System.out.println("Scheduled Transactions:");
            for (ScheduledTransaction transaction : scheduledTransactions) {
                System.out.println(transaction.toString());
            }
        }
    }

    // Method to display Account Alerts
    static void displayAccountAlerts() {
        // Check for unusual activity and send alerts if necessary
        String username = "exampleUser";
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



    /*private static void displayMultiCurrencySupport() {
        // Convert currency and display the result
        CurrencyConverter currencyConverter = new CurrencyConverter();
        double convertedAmount = currencyConverter.convertCurrency(100.0, "USD", "EUR");
        System.out.println("Converted amount: " + convertedAmount);
    }*/

    // Method to display User Profile Management
    private static void displayUserProfileManagement() {
        // Retrieve and display user profile
        String name = "";
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
