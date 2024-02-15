package Transactions.AdvancedFeatures;

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

    public double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        // Get exchange rates for the currencies
        double fromRate = exchangeRates.getOrDefault(fromCurrency, 0.0);
        double toRate = exchangeRates.getOrDefault(toCurrency, 0.0);

        // Perform currency conversion
        double convertedAmount = (amount / fromRate) * toRate;
        return convertedAmount;
    }

}
