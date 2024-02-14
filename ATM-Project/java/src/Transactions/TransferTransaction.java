package Transactions;

import java.time.LocalDateTime;

public class TransferTransaction implements Transaction {
    private double amount;
    private String username;
    private String recipient;
    private LocalDateTime timestamp;

    public TransferTransaction(double amount, String username, String recipient) {
        this.amount = amount;
        this.username = username;
        this.recipient = recipient;
        this.timestamp = LocalDateTime.now(); // Record the current timestamp
    }

    public double getAmount() {
        return amount;
    }

    public String getUsername() {
        return username;
    }

    public String getRecipient() {
        return recipient;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public void execute() {

    }
}
