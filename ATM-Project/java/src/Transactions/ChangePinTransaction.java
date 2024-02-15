package Transactions;

import java.io.IOException;
import java.util.List;
import FileManager.*;

public class ChangePinTransaction implements Transaction{

    private String username;
    private String newPin;

    public ChangePinTransaction(String username, String newPin) {
        this.username = username;
        this.newPin = newPin;
    }

    @Override
    public void execute() throws IOException {
        // Logic to change user PIN
        FileManager fileManager = FileManager.getInstance();
        List<String> userRecords = fileManager.readUserRecords();

        for (int i = 0; i < userRecords.size(); i++) {
            String[] parts = userRecords.get(i).split(",");
            if (parts[0].equals(username)) {
                parts[1] = newPin; // Change PIN
                userRecords.set(i, String.join(",", parts));
                break;
            }
        }

        fileManager.writeUserRecords(userRecords);
        System.out.println("PIN change successful.");
    }

}
