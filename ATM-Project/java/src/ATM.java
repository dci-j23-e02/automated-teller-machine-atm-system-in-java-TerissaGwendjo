import User.*;

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

            // Display menu
            System.out.println("Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Change PIN");
            System.out.println("5. Exit");
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
                    Transaction transferTransaction = TransactionFactory.createTransferTransaction("TRANSFER",username,receiverUsername,transferAmount);
                    transferTransaction.execute();
                    break;
                case 4:
                    System.out.print("Enter new PIN: ");
                    String newPin = scanner.next();
                    Transaction changePinTransaction = new ChangePinTransaction(username, newPin);
                    changePinTransaction.execute();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
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

    private static void displayBalance(String username, List<String> userRecords) {
        for (String record : userRecords) {
            String[] parts = record.split(",");
            if (parts[0].equals(username)) {
                System.out.println("Your current balance is: " + parts[2]);
                break;
            }
        }
    }

}
