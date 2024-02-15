package Transactions.AdvancedFeatures;

import Transactions.Transaction;
import Transactions.TransactionFactory;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryManager {

    private static List<TransactionFactory> transactionHistory;

    public TransactionHistoryManager() {
        this.transactionHistory = new ArrayList<>();
    }

    public static List<Transaction> getTransactionHistory() {
        return null;
    }

    public void addTransaction(TransactionFactory transaction) {
        transactionHistory.add(transaction);
    }

    public static List<TransactionFactory> getTransactionHistory(String username) {
        List<TransactionFactory> userTransactions = new ArrayList<>();
        for (TransactionFactory transaction : transactionHistory) {
            if (transaction.getUsername().equals(username)) {
                userTransactions.add(transaction);
            }
        }
        return userTransactions;
    }

}
