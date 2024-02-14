package User;
import FileManager.*;

import java.util.HashMap;

// Class to manage user-related operations like authentication, balance inquiries, and password changes
public class UserManager {
    private FileManager fileManager; // FileManager instance to manage file operations
    private HashMap<String, User> users; // HashMap to store user data

    // Constructor for the UserManager class
    public UserManager() {
        // Initialize the FileManager instance
        fileManager = FileManager.getInstance();
        // Read user records from the file and initialize the users HashMap
        users = fileManager.readUserRecords();
    }

    // Method to authenticate a user based on username and password
    public boolean authenticateUser(String username, String password) {
        // Check if the provided username exists in the users HashMap
        if (users.containsKey(username)) {
            // If the username exists, retrieve the corresponding user object
            User user = users.get(username);
            // Check if the provided password matches the user's password
            return user.getPassword().equals(password);
        }
        // If the username doesn't exist, authentication fails
        return false;
    }

    // Method to get the balance of a user based on username
    public double getBalance(String username) {
        // Retrieve the user object from the users HashMap based on the username
        User user = users.get(username);
        // Return the balance of the user
        return user.getBalance();
    }

    // Method to deposit funds into a user's account
    public void deposit(String username, double amount) {
        // Retrieve the user object from the users HashMap based on the username
        User user = users.get(username);
        // Check if the user object is null
        if (user != null) {
            // Update the user's balance by adding the deposited amount
            user.setBalance(user.getBalance() + amount);
            // Update the user records in the file
            fileManager.updateUserRecords(users);
        } else {
            // Handle the case where the user is not found
            System.out.println("User not found.");
        }
    }

    // Method to withdraw funds from a user's account
    public boolean withdraw(String username, double amount) {
        // Retrieve the user object from the users HashMap based on the username
        User user = users.get(username);
        // Check if the user has sufficient balance for the withdrawal
        if (user.getBalance() >= amount) {
            // If the balance is sufficient, update the user's balance by subtracting the withdrawal amount
            user.setBalance(user.getBalance() - amount);
            // Update the user records in the file
            fileManager.updateUserRecords(users);
            // Return true to indicate successful withdrawal
            return true;
        }
        // If the balance is insufficient, return false to indicate failed withdrawal
        return false;
    }

    // Method to transfer funds from one user to another
    public boolean transfer(String sender, String recipient, double amount) {
        // Check if the sender has sufficient balance for the transfer
        if (withdraw(sender, amount)) {
            // If the withdrawal is successful, deposit the amount to the recipient's account
            deposit(recipient, amount);
            // Return true to indicate successful transfer
            return true;
        }
        // If the sender doesn't have sufficient balance, return false to indicate failed transfer
        return false;
    }

    // Method to change the password of a user
    public void changePassword(String username, String newPassword) {
        // Retrieve the user object from the users HashMap based on the username
        User user = users.get(username);
        // Update the user's password with the new password
        user.setPassword(newPassword);
        // Update the user records in the file
        fileManager.updateUserRecords(users);
    }
}

