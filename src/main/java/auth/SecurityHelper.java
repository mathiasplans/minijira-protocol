package auth;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class SecurityHelper {

    /**
     * @param password a password entered by user.
     * @param salt 32 bytes long salt, can be stored in plain text.
     * @return password hash for the given password and salt.
     */
    public static byte[] passwordToHash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterationCount = 1_000_000;

        // output length of SHA-256 (32 bytes)
        int outputLengthBits = 256;

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, outputLengthBits);
        return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
    }

    public static byte[] generateSalt() {
        // generate the salt
        SecureRandom rng = new SecureRandom();
        byte[] salt = new byte[64];
        rng.nextBytes(salt);
        return salt;
    }

    public static byte[] generateSessionKey() {
        SecureRandom rng = new SecureRandom();
        byte[] key = new byte[32];
        rng.nextBytes(key);
        return key;
    }

}
