package FileManager;
import User.User;

import java.io.*;
import java.util.HashMap;


// Singleton class to manage file operations for user data storage
public class FileManager {
    private static FileManager instance;
    private File userRecordsFile;

    private FileManager() {
        // Initialize the file object to represent the UserData.txt file
        userRecordsFile = new File("ATM-Project/java/src/User/UserData");

        // Check if the file doesn't exist
        if (!userRecordsFile.exists()) {
            try {
                // Attempt to create a new file if it doesn't exist
                userRecordsFile.createNewFile();
            } catch (IOException e) {
                // Print stack trace if an error occurs during file creation
                e.printStackTrace();
            }
        }
    }

    // Synchronized keyword ensures that only one thread can access this method at a time,
    // preventing the creation of multiple instances in a multithreaded environment.
    public static synchronized FileManager getInstance() {
        //Check if the singleton instance is null, indicating that it hasn't been initialized yet.
        if (instance == null) {

            // Create a new instance of the FileManager class and assign it to the instance variable.
            instance = new FileManager();
        }
        // Return the singleton instance of the FileManager class.
        return instance;
    }

    // Read user records from the file and return a HashMap of username to User object
    public HashMap<String, User> readUserRecords() {
        // Create a HashMap to store user records
        HashMap<String, User> users = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(userRecordsFile))) {
            String line;
            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                // Split the line into username, password, and balance using comma as delimiter
                String[] userData = line.split(",");
                String username = userData[0];
                String password = userData[1];
                double balance = Double.parseDouble(userData[2]);

                // Create a new User object and add it to the HashMap
                users.put(username, new User(username, password, balance));
            }
        } catch (IOException e) {
            // Print stack trace if an error occurs during file reading
            e.printStackTrace();
        }
        // Return the HashMap containing user records
        return users;
    }

    // Update user records in the file after changes
    public void updateUserRecords(HashMap<String, User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userRecordsFile))) {
            // Iterate through the HashMap containing user records
            for (User user : users.values()) {
                // Write username, password, and balance to the file separated by commas
                writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getBalance() + "\n");
            }
        } catch (IOException e) {
            // Print stack trace if an error occurs during file writing
            e.printStackTrace();
        }
    }

}