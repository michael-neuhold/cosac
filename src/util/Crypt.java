package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypt {

    private MessageDigest messageDigest;

    public Crypt() throws NoSuchAlgorithmException {
        messageDigest = MessageDigest.getInstance("SHA-256");
    }

    public String getHash(String password) {
        messageDigest.update(password.getBytes());
        String hash = getHexString().toString();
        Logger.clientStatus("generated hash: ## " + hash + " ##");
        return hash;
    }

    private StringBuilder getHexString() {
        byte[] digest = messageDigest.digest();
        StringBuilder hexString = new StringBuilder();
        for (int i = 0;i<digest.length;i++)
            hexString.append(Integer.toHexString(0xFF & digest[i]));
        return hexString;
    }

}
