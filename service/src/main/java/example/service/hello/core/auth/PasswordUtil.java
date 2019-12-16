package example.service.hello.core.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utility class for working with passwords.
 */
public class PasswordUtil {
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    private PasswordUtil() {
        // Noop
    }

    /**
     * Encodes the raw value with BCrypt.
     *
     * @param rawValue raw password to encode
     * @return encoded value
     */
    public static String encode(String rawValue) {
        return ENCODER.encode(rawValue);
    }

    /**
     * Checks if the supplied raw value matches the encoded value.
     *
     * @param rawValue raw password
     * @param encodedValue encoded password
     * @return <code>true</code> if the values match; otherwise <code>false</code>
     */
    public static boolean matches(String rawValue, String encodedValue) {
        return ENCODER.matches(rawValue, encodedValue);
    }

    public static void main(String... args) {
        if (args.length == 1) {
            System.out.println("Encoded Password: " + encode(args[0]));
        } else if (args.length == 2) {
            if (matches(args[0], args[1])) {
                System.out.println("Passwords Match!");
            } else {
                System.out.println("Passwords Do Not Match!");
            }
        } else {
            throw new IllegalArgumentException("Invalid number of parameters!");
        }
    }
}