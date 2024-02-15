package Transactions.AdvancedFeatures;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class SecurityManager {

    private UserService userService;

    public SecurityManager(UserService userService) {
        this.userService = userService;
    }

    public SecurityManager() {
    }

    public boolean authenticateUser(String username, String password) {
        return userService.authenticate(username, password);
    }
    public boolean enforceAuthorization(String username, String resource) {
        return userService.hasPermission(username, resource);
    }

    private static final String AES_SECRET_KEY = "AHFGJ7856"; // a secret key

    public String encryptData(String data) {
        try {
            // Create AES cipher
            Cipher cipher = Cipher.getInstance("AES");
            SecretKey secretKey = new SecretKeySpec(AES_SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // Encrypt the data
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());

            // Encode the encrypted bytes to Base64 string
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null on error
        }
    }

    public String decryptData(String encryptedData) {
        try {
            // Create AES cipher
            Cipher cipher = Cipher.getInstance("AES");
            SecretKey secretKey = new SecretKeySpec(AES_SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Decode the Base64 string to bytes
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);

            // Decrypt the data
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            // Convert the decrypted bytes to string
            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null on error
        }
    }


}

