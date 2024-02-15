package Transactions;
import User.*;

public abstract class TransactionFactory {
    // Method to create different Transaction objects based on the transaction type
    public static Transaction createTransaction(String transactionType, String senderUsername, double amount) {
        // Check the transaction type and return the corresponding Transaction object
        switch (transactionType) {
            case "DEPOSIT":
                return new DepositTransaction(amount, senderUsername);
            case "WITHDRAWAL":
                return new WithdrawalTransaction(amount, senderUsername);
            default:
                // Handle unknown transaction types or invalid inputs
                throw new IllegalArgumentException("Invalid transaction type: " + transactionType);
        }
    }

    public static Transaction createTransferTransaction(String TransactionType, String senderUsername, String receiverUsername, double amount) {
        return new TransferTransaction(amount, senderUsername, receiverUsername);
    }
    public static Transaction createChangePinTransaction(String TransactionType, String username, String newPin) {
        return new ChangePinTransaction(username, newPin);
    }


}
