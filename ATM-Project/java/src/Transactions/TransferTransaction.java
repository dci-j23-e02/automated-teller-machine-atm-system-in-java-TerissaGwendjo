package Transactions;

import FileManager.*;

import java.io.IOException;
import java.util.List;

public abstract class TransferTransaction implements Transaction {
    private double amount;
    private String senderUsername;
    private String receiverUsername;

    public TransferTransaction( double amount, String senderUsername, String receiverUsername) {
        this.amount = amount;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
    }

    @Override
    public void execute() throws IOException {
        // Logic to transfer money from sender to receiver
        FileManager fileManager = FileManager.getInstance();
        List<String> userRecords = fileManager.readUserRecords();

        double senderBalance = 0;
        double receiverBalance = 0;

        // Update sender's balance
        for (int i = 0; i < userRecords.size(); i++) {
            String[] parts = userRecords.get(i).split(",");
            if (parts[0].equals(senderUsername)) {
                senderBalance = Double.parseDouble(parts[2]);
                if (senderBalance >= amount) {
                    senderBalance -= amount;
                    parts[2] = String.valueOf(senderBalance);
                    userRecords.set(i, String.join(",", parts));
                } else {
                    System.out.println("Insufficient balance for transfer.");
                    return;
                }
                break;
            }
        }

        // Update receiver's balance
        for (int i = 0; i < userRecords.size(); i++) {
            String[] parts = userRecords.get(i).split(",");
            if (parts[0].equals(receiverUsername)) {
                receiverBalance = Double.parseDouble(parts[2]);
                receiverBalance += amount;
                parts[2] = String.valueOf(receiverBalance);
                userRecords.set(i, String.join(",", parts));
                break;
            }
        }

        FileManager.writeUserRecords(userRecords);
        System.out.println("Transfer successful.");
    }
}
