package com.codecool.shop.config;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class PasswordUtil {
    private static Random random = new Random();
    private static Base64.Encoder encoder = Base64.getEncoder();
    private static Base64.Decoder decoder = Base64.getDecoder();
    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 128;
    private static final int SALT_LENGTH = 16;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";

    private static byte[] getHash(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec keySpec = new PBEKeySpec(password, salt, ITERATION_COUNT, KEY_LENGTH);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] hash = keyFactory.generateSecret(keySpec).getEncoded();
        byte[] hashWithSalt = new byte[salt.length + hash.length];

        System.arraycopy(salt, 0, hashWithSalt, 0, salt.length);
        System.arraycopy(hash, 0, hashWithSalt, salt.length, hash.length);
        return hashWithSalt;
    }

    private static byte[] hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        char[] passwordChars = password.toCharArray();
        return getHash(passwordChars, salt);
    }

    public static String hashPassword(String password) {
        char[] passwordChars = password.toCharArray();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        try {
            return encoder.encodeToString(getHash(passwordChars, salt));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean validatePassword(String password, String hashedPw) {
        byte[] hash = decoder.decode(hashedPw);
        byte[] salt = new byte[SALT_LENGTH];
        System.arraycopy(hash, 0, salt, 0, SALT_LENGTH);
        try {
            return Arrays.equals(hashPassword(password, salt), hash);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }
}
