package Transactions.AdvancedFeatures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseUserService implements UserService{

    // Simulated database storing user credentials and permissions
    private Map<String, String> userCredentials; // username -> password
    private Map<String, String> userPermissions; // username -> resource

    public DatabaseUserService() {
        // Initialize the simulated database by reading from UserData.txt
        userCredentials = new HashMap<>();
        userPermissions = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("ATM-Project/java/src/User/UserData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String username = parts[0];
                    String password = parts[1];
                    String resource = parts[2];
                    userCredentials.put(username, password);
                    userPermissions.put(username, resource);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean authenticate(String username, String password) {
        // Check if the provided username exists in the database
        if (userCredentials.containsKey(username)) {
            // If the username exists, check if the provided password matches the stored password
            String storedPassword = userCredentials.get(username);
            if (storedPassword.equals(password)) {
                // Authentication successful
                return true;
            }
        }
        // Authentication failed
        return false;
    }

    @Override
    public boolean hasPermission(String username, String resource) {
        // Check if the provided username has permission to access the specified resource
        if (userPermissions.containsKey(username)) {
            // If the username exists in the permissions database, check if it has access to the resource
            String permittedResource = userPermissions.get(username);
            if (permittedResource.equals(resource)) {
                // User has permission to access the resource
                return true;
            }
        }
        // User does not have permission to access the resource
        return false;
    }
}


