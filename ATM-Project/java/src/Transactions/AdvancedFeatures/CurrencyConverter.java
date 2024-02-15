package Transactions.AdvancedFeatures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {

    // Sample exchange rates
    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        // Populate exchange rates (for demonstration purposes)
        exchangeRates.put("USD", 1.0); // 1 USD = 1 USD
        exchangeRates.put("EUR", 0.88); // 1 USD = 0.88 EUR
        exchangeRates.put("GBP", 0.78); // 1 USD = 0.78 GBP

    }

    // Method to read account balance from file or any other data source
    public static double readAccountBalanceFromFile() {
        // Implement logic to read account balance from file
        // For example:
        double accountBalance = 0.0;
        try (BufferedReader reader = new BufferedReader(new FileReader("ATM-Project/java/src/User/UserData.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                // Assuming balance is stored as the third element in a comma-separated line
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String balanceString = parts[2]; // Assuming balance is the third element
                    accountBalance = Double.parseDouble(balanceString);
                } else {
                    throw new IOException("Invalid data format: Unable to parse balance.");
                }
            } else {
                throw new IOException("Empty file: Unable to read balance.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return accountBalance;
    }

    public double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        // Get exchange rates for the currencies
        double fromRate = exchangeRates.getOrDefault(fromCurrency, 0.0);
        double toRate = exchangeRates.getOrDefault(toCurrency, 0.0);

        // Perform currency conversion
        double convertedAmount = (amount / fromRate) * toRate;
        return convertedAmount;
    }


}
