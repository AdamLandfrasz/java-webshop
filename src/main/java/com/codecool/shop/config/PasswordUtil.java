package com.codecool.shop.config;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.lang.reflect.Array;
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

    private static byte[] getHash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = keyFactory.generateSecret(spec).getEncoded();
        byte[] saltedHash = new byte[salt.length + hash.length];

        System.arraycopy(salt, 0, saltedHash, 0, salt.length);
        System.arraycopy(hash, 0, saltedHash, salt.length, hash.length);
        return saltedHash;
    }

    private static byte[] hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return getHash(password, salt);
    }

    public static String hashPassword(String password) {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        try {
            return encoder.encodeToString(getHash(password, salt));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean validatePassword(String password, String hashedPw) {
        byte[] hash = decoder.decode(hashedPw);
        byte[] salt = new byte[16];
        System.arraycopy(hash, 0, salt, 0, 16);
        try {
            return Arrays.equals(hashPassword(password, salt), hash);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }
}
