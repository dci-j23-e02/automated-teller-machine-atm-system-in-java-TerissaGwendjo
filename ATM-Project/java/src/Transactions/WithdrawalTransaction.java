package Transactions;

import java.time.LocalDateTime;

public class WithdrawalTransaction implements Transaction {
    private double amount;
    private String username;
    private LocalDateTime timestamp;

    public WithdrawalTransaction(double amount, String username) {
        this.amount = amount;
        this.username = username;
        this.timestamp = LocalDateTime.now(); // Record the current timestamp
    }

    public double getAmount() {
        return amount;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public void execute() {

    }
}