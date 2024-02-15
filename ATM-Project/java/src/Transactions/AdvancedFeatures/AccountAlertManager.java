package Transactions.AdvancedFeatures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountAlertManager {
        private Map<String, Double> accountBalances;
    private static Map<String, List<Double>> transactionHistory;

        public AccountAlertManager() {
            this.accountBalances = new HashMap<>();
            this.transactionHistory = new HashMap<>();
        }

        public void checkAccountBalance(String username, double balanceThreshold) {
            Double balance = accountBalances.get(username);
            if (balance != null && balance < balanceThreshold) {
                // Send alert about low balance
                System.out.println("ALERT: Your account balance is below threshold!");
            }
        }

    // Method to record transactions for each user
    public void recordTransaction(String username, double amount) {
        List<Double> userTransactions = transactionHistory.getOrDefault(username, new ArrayList<>());
        userTransactions.add(amount);
        transactionHistory.put(username, userTransactions);
    }

    // Check unusual activity
    public static void checkUnusualActivity(String username) {
        List<Double> userTransactions = transactionHistory.get(username);
        if (userTransactions != null) {
            // Check for unusual activity based on the user's transaction history
            double totalAmount = 0.0;
            for (Double amount : userTransactions) {
                totalAmount += amount;
            }
            double averageTransactionAmount = totalAmount / userTransactions.size();

            // Criteria for unusual activity defined, such as transactions above a certain threshold
            double threshold = averageTransactionAmount * 2; // Example: twice the average transaction amount

            for (Double amount : userTransactions) {
                if (amount > threshold) {
                    // Unusual activity detected, send alert
                    System.out.println("ALERT: Unusual activity detected for user " + username);
                    return;
                }
            }
        }
    }

}
