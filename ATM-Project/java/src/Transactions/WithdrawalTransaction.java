package Transactions;

import FileManager.*;

import java.io.IOException;
import java.util.List;

public class WithdrawalTransaction implements Transaction {

    private double amount;
    private String username;

    public WithdrawalTransaction(double amount, String username) {
        this.username = username;
        this.amount = amount;
    }

    @Override
    public void execute() throws IOException {
        // Logic to withdraw money from the account
        FileManager fileManager = FileManager.getInstance();
        List<String> userRecords = fileManager.readUserRecords();

        for (int i = 0; i < userRecords.size(); i++) {
            String[] parts = userRecords.get(i).split(",");
            if (parts[0].equals(username)) {
                double currentBalance = Double.parseDouble(parts[2]);
                if (currentBalance >= amount) {
                    currentBalance -= amount;
                    parts[2] = String.valueOf(currentBalance);
                    userRecords.set(i, String.join(",", parts));
                    FileManager.writeUserRecords(userRecords);
                    System.out.println("Withdrawal successful.");
                } else {
                    System.out.println("Insufficient balance.");
                }
                break;
            }
        }
    }
}
