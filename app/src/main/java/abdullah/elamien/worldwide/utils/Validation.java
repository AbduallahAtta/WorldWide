package abdullah.elamien.worldwide.utils;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by AbdullahAtta on 10/17/2018.
 */
public class Validation {
    private static final String PASSWORD_PATTERN = "(" +
            "(?=.*\\d)" +
            "(?=.*[!@#$%^&*()])" +
            ".{6,20}" +
            ")";

    public static boolean isEmailType(String text) {
        return Patterns.EMAIL_ADDRESS.matcher(text).matches() && !TextUtils.isEmpty(text);
    }

    public static boolean isValidPassword(String text) {
        return Pattern.compile(PASSWORD_PATTERN).matcher(text).matches();
    }
}
