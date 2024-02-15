package Transactions;

import java.io.IOException;

public interface Transaction {
    String getUsername();
    void execute() throws IOException; // Method to execute the transaction
}