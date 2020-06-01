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
        byte[] digest = messageDigest.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0;i<digest.length;i++) {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
        return hexString.toString();
    }

}
