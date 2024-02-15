package Transactions;

import java.io.IOException;

public interface Transaction {
    void execute() throws IOException; // Method to execute the transaction
}