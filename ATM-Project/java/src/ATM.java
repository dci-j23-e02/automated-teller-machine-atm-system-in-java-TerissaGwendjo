import User.*;

import java.util.Scanner;


public class ATM {

    private UserManager userManager;
    private Scanner scanner;

    public ATM() {
        userManager = new UserManager();
        scanner = new Scanner(System.in);
    }

    // Main method to drive the ATM system
    public void run() {
        boolean isAuthenticated = false;
        String username = "";
        System.out.println("Welcome to the ATM System");

        // User authentication
        while (!isAuthenticated) {
            System.out.print("Enter your username: ");
            String inputUsername = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            isAuthenticated = userManager.authenticateUser(inputUsername, password);
            if (isAuthenticated) {
                username = inputUsername;
                System.out.println("Authentication successful!");
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

        // Display balance
        System.out.println("Your balance: $" + userManager.getBalance(username));

        // Deposit funds
        System.out.print("Enter amount to deposit: ");
        double depositAmount = Double.parseDouble(scanner.nextLine());
        userManager.deposit(username, depositAmount);
        System.out.println("Deposit successful!");

        // Withdraw funds
        System.out.print("Enter amount to withdraw: ");
        double withdrawAmount = Double.parseDouble(scanner.nextLine());
        boolean isWithdrawn = userManager.withdraw(username, withdrawAmount);
        if (isWithdrawn) {
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }

        // Transfer funds
        System.out.print("Enter recipient's username: ");
        String recipient = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double transferAmount = Double.parseDouble(scanner.nextLine());
        boolean isTransferred = userManager.transfer(username, recipient, transferAmount);
        if (isTransferred) {
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Transfer failed.");
        }

        // Change password
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        userManager.changePassword(username, newPassword);
        System.out.println("Password changed successfully!");

        scanner.close();
    }

    public static void main(String[] args) {
        ATM atmSystem = new ATM();
        atmSystem.run();
    }

}
