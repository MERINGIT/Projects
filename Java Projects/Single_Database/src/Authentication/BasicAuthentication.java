package Authentication;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * The BasicAuthentication class handles user authentication using a CSV file for credentials.
 */
public class BasicAuthentication {
    private final ReadCredentialsFromCSVForAuthentication credentialsReader = new ReadCredentialsFromCSVForAuthentication();
    String csvFile = "credentials.csv";

    /**
     * Initiates the authentication process.
     */
    public String authenticate() {
        List<UserDetails> credentials = credentialsReader.readCredentials(csvFile);
        Scanner scanner = new Scanner(System.in);
        int maxAttempts = 3;
        int count = 0;

        while (count < maxAttempts) {
            System.out.println("------------Basic Authentication-------------");
            System.out.println("Please sign-in-------");
            System.out.println("Enter the Username:");
            String username = scanner.nextLine();
            System.out.println("Enter the Password:");
            String password = scanner.nextLine();
            System.out.println("Enter the Captcha:D123");
            String captcha = scanner.nextLine();

            if (isValidCredentials(username, password, captcha, credentials)) {
                System.out.println("Authentication successful!");
                return username;
            } else {
                System.out.println("Authentication failed. Invalid username, password, or captcha.");
                count++;
            }
        }
        System.out.println("You attempted " + maxAttempts + " times. You can't log in now.");
        exit(0);
        return null;
    }

    /**
     * Checks if the provided credentials match any stored credentials.
     *
     * @param username   The username provided by the user.
     * @param password   The password provided by the user.
     * @param captcha    The captcha provided by the user.
     * @param credentials The list of stored user credentials.
     * @return True if the credentials match, false otherwise.
     */
    private boolean isValidCredentials(String username, String password, String captcha, List<UserDetails> credentials) {
        for (UserDetails user : credentials) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password) &&
                    user.getCaptcha().equals(captcha)) {
                return true;
            }
        }
        return false;
    }
}
