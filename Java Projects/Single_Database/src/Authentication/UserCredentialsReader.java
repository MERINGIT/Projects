package Authentication;

import java.util.List;

/**
 * An interface for reading user credentials from a CSV file for authentication.
 */
public interface UserCredentialsReader {
    /**
     * Reads user credentials from a CSV file.
     *
     * @param source The path to the CSV file containing user credentials.
     * @return A list of UserDetails objects representing user credentials.
     */
    List<UserDetails> readCredentials(String source);
}

