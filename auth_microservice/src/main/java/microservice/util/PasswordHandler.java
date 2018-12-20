package microservice.util;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;


@Component
public class PasswordHandler {

    private static int workload = 12;

    public static String encryptPassword(String plaintextPassword) {
        String salt = BCrypt.gensalt(workload);
        String hashedPassword = BCrypt.hashpw(plaintextPassword, salt);
        return hashedPassword;
    }

    public static boolean checkPassword(String plaintextPassword, String storedHash) {
        boolean verifiedPassword = false;

        if (null == storedHash || !storedHash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("invalid hash provided for comparison");

        verifiedPassword = BCrypt.checkpw(plaintextPassword, storedHash);

        return verifiedPassword;
    }
}