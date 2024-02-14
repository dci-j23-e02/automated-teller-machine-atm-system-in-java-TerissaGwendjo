package Transactions;

import java.time.LocalDateTime;

public class DepositTransaction implements Transaction{
    private double amount;
    private String username;
    private LocalDateTime timestamp;

    public DepositTransaction(double amount, String accountNumber) {
        this.amount = amount;
        this.username = accountNumber;
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
