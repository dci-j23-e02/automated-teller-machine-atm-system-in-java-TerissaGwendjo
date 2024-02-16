package Transactions.AdvancedFeatures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserProfile {
        private String username;
        private String password;
        private double balance;
        private String name;
        private String email;
        private String dateOfBirth;

        public UserProfile(String username, String password, double balance, String name, String email, String dateOfBirth) {
            this.username = username;
            this.password = password;
            this.balance = balance;
            this.name = name;
            this.email = email;
            this.dateOfBirth = dateOfBirth;
        }

    public UserProfile(String name, String email, String dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    // Constructor to read user profile from file
        public UserProfile(String username, String fileName) {
            try (Scanner scanner = new Scanner(new File(fileName))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] userData = line.split(",");
                    if (userData.length >= 6 && userData[0].equals(username)) { // Ensure all fields are present
                        this.username = userData[0];
                        this.password = userData[1];
                        this.balance = Double.parseDouble(userData[2]);
                        this.name = userData[3];
                        this.email = userData[4];
                        this.dateOfBirth = userData[5];
                        return; // Exit loop if user is found
                    }
                }
                // If user is not found, set default values or handle appropriately
                this.username = "Unknown";
                this.password = "";
                this.balance = 0.0;
                this.name = "";
                this.email = "";
                this.dateOfBirth = "";
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + fileName);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing balance: " + e.getMessage());
            }
        }

        // Getters for user profile attributes
        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public double getBalance() {
            return balance;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }
}
