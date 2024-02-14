package Transactions;
import User.*;

public class TransactionFactory {
    // Method to create different Transaction objects based on the transaction type
    public Transaction createTransaction(String transactionType, double amount, String senderAccountNumber, String recipientAccountNumber) {
        // Check the transaction type and return the corresponding Transaction object
        switch (transactionType) {
            case "deposit":
                return new DepositTransaction(amount, recipientAccountNumber);
            case "withdrawal":
                return new WithdrawalTransaction(amount, senderAccountNumber);
            case "transfer":
                return new TransferTransaction(amount, senderAccountNumber, recipientAccountNumber);
            default:
                // Handle unknown transaction types or invalid inputs
                throw new IllegalArgumentException("Invalid transaction type: " + transactionType);
        }
    }
}
