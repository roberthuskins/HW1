package edu.csumb.rhuskinshw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginUtils {
    private static final List<String> PASSWORDS = new ArrayList<>(Arrays.asList("password1", "password2", "password3"));

    public static boolean verifyPassword(String password, int userId) {
        return PASSWORDS.get(userId).equals(password);
    }
}
