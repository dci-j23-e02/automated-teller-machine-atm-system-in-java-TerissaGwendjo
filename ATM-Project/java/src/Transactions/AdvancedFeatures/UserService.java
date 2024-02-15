package Transactions.AdvancedFeatures;

public interface UserService {

    // Authenticates a user based on the provided username and password.
    boolean authenticate(String username, String password);

    //Checks if a user has permission to access a specific resource.
    boolean hasPermission(String username, String resource);

}
