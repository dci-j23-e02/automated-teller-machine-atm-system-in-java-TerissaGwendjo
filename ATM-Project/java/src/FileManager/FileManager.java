package FileManager;
import User.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


// Singleton class to manage file operations for user data storage
public class FileManager {
    private static FileManager instance;
    private static File userRecordsFile;

    private FileManager() {
        // Initialize the file object to represent the UserData.txt.txt file
        userRecordsFile = new File("ATM-Project/java/src/User/UserData.txt");

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

    public List<String> readUserRecords() throws IOException {
        List<String> userRecords = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(userRecordsFile));
        String line;
        while ((line = reader.readLine()) != null) {
            userRecords.add(line);
        }
        reader.close();
        return userRecords;
    }

    public static void writeUserRecords(List<String> userRecords) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(userRecordsFile));
        for (String record : userRecords) {
            writer.write(record);
            writer.newLine();
        }
        writer.close();
    }


}