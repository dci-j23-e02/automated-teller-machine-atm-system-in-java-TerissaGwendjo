import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import Transactions.*;
import FileManager.*;


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
            System.out.println("5. Exit");
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
                    Transaction changePinTransaction = new ChangePinTransaction(username, newPin);
                    // Execute change PIN transaction
                    changePinTransaction.execute();
                    break;
                case 5:
                    // Exit
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } catch (IOException e) {
            // Handle IO exception
            System.err.println("Error reading/writing user records file: " + e.getMessage());
        }
    }

    private static boolean authenticateUser(String username, List<String> userRecords) {
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

    private static void displayBalance(String username, List<String> userRecords) {
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

}
