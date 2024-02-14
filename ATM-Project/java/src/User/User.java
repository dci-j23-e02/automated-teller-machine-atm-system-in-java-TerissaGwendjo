package User;
import Transactions.*;

import java.util.ArrayList;
import java.util.List;

// User class to represent a user with username, password, and balance
public class User {
    private String username;
    private String password;
    private double balance;
    private String currency;
    public String getCurrency() {
        return currency;
    }

    private List<Transaction> transactionHistory; // stores all transactions associated with the user

    // Constructor
    public User(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

}
