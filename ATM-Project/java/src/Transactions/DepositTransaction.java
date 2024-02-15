package Transactions;
import FileManager.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class DepositTransaction implements Transaction{
    private double amount;
    private String username;


    public DepositTransaction(double amount, String username) {
        this.amount = amount;
        this.username = username;

    }

    @Override
    public void execute() throws IOException {
        // Logic to deposit money into the account
        FileManager fileManager = FileManager.getInstance();
        List<String> userRecords = fileManager.readUserRecords();

        for (int i = 0; i < userRecords.size(); i++) {
            String[] parts = userRecords.get(i).split(",");
            if (parts[0].equals(username)) {
                double currentBalance = Double.parseDouble(parts[2]);
                currentBalance += amount;
                parts[2] = String.valueOf(currentBalance);
                userRecords.set(i, String.join(",", parts));
                break;
            }
        }

        FileManager.writeUserRecords(userRecords);
        System.out.println("Deposit successful.");
    }
}
