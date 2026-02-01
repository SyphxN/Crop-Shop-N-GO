package main.java.login_register;

import java.io.File;
import java.io.IOException;

/**
 * Path to the local users file. Stored in the current user's home directory
 * so each person only sees their own data (not shared with others or in the project).
 */
public final class UsersFile {

    private static final String APP_DIR = ".cropshopngo";
    private static final String FILE_NAME = "users.txt";

    private UsersFile() {}

    /**
     * Returns the path to the users file in the user's home directory.
     * Example: home/.cropshopngo/users.txt (Mac/Linux and Windows).
     */
    public static String getPath() {
        String home = System.getProperty("user.home");
        if (home == null) home = ".";
        return home + File.separator + APP_DIR + File.separator + FILE_NAME;
    }

    /**
     * Ensures the parent directory exists. Call before writing.
     */
    public static void ensureParentDirExists() throws IOException {
        File file = new File(getPath());
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
    }
}
