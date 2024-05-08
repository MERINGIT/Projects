package Authentication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The ReadCredentialsFromCSVForAuthentication class reads user credentials from a CSV file for authentication.
 */
public class ReadCredentialsFromCSVForAuthentication  implements  UserCredentialsReader{
    /**
     * Reads user credentials from a CSV file.
     *
     * @param source The path to the CSV file containing user credentials.
     * @return A list of UserDetails objects representing user credentials.
     */
    public List<UserDetails> readCredentials(String source) {
        List<UserDetails> values = new ArrayList<>();
        UserDetails temp;

        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String username = parts[0];
                    String password = parts[1];
                    String captcha = parts[2];
                    temp = new UserDetails(username, password, captcha);
                    values.add(temp);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading source: " + e.getMessage());
        }

        return values;
    }
}
