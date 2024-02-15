package Transactions.AdvancedFeatures;

import java.util.HashMap;
import java.util.Map;

public class UserProfileManager {

    private static Map<String, UserProfile> userProfiles;

    public UserProfileManager() {
        this.userProfiles = new HashMap<>();
    }

    public void updateUserProfile(String username, UserProfile userProfile) {
        userProfiles.put(username, userProfile);
    }

    public static UserProfile getUserProfile(String username) {
        return userProfiles.get(username);
    }

}
