package main.java.login_register;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * One-way hash for storing passwords; not reversible.
 */
public final class PasswordHash {

    private static final String SALT_PREFIX = "CropShopNGo:";
    private static final int HEX_HASH_LENGTH = 64; // SHA-256 hex length

    private PasswordHash() {}

    /**
     * Hash password with per-user salt. Same password + username always gives same hash.
     */
    public static String hash(String username, String password) {
        if (password == null) password = "";
        String salted = SALT_PREFIX + (username != null ? username : "") + ":" + password;
        return sha256Hex(salted);
    }

    /**
     * Check if the stored value is a hash (64 hex chars).
     */
    public static boolean isStoredHash(String stored) {
        if (stored == null || stored.length() != HEX_HASH_LENGTH) return false;
        for (int i = 0; i < stored.length(); i++) {
            char c = stored.charAt(i);
            if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')) continue;
            return false;
        }
        return true;
    }

    /**
     * Verify password against stored value (hash or legacy plain text).
     */
    public static boolean verify(String username, String password, String stored) {
        if (stored == null) return false;
        if (isStoredHash(stored)) {
            return hash(username, password).equals(stored);
        }
        return stored.equals(password);
    }

    private static String sha256Hex(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
    }
}
