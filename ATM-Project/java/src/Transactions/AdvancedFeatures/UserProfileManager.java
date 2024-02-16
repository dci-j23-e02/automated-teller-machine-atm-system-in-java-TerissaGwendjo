package Transactions.AdvancedFeatures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserProfileManager {

    private static Map<String, UserProfile> userProfiles;

    public UserProfileManager() {
        this.userProfiles = new HashMap<>();
        loadUserProfilesFromFile("ATM-Project/java/src/User/UserData.txt");
    }

    // Method to load user profiles from file
    private void loadUserProfilesFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] userData = line.split(",");
                if (userData.length >= 6) { // Ensure all fields are present
                    String username = userData[0];
                    String password = userData[1];
                    double balance = Double.parseDouble(userData[2]);
                    String name = userData[3];
                    String email = userData[4];
                    String dateOfBirth = userData[5];

                    // Create a UserProfile object and add it to the map
                    UserProfile userProfile = new UserProfile( name, email, dateOfBirth);
                    userProfiles.put(username, userProfile);
                } else {
                    System.err.println("Invalid user data format: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing balance: " + e.getMessage());
        }
    }


    public void updateUserProfile(String username, UserProfile userProfile) {
        userProfiles.put(username, userProfile);
    }

    public static UserProfile getUserProfile(String username) {

        if (userProfiles == null) {
            System.err.println("User profiles are not initialized.");
            return null;
        }
        return userProfiles.get(username);
    }

}
