import Authentication.BasicAuthentication;
import CommandLineInterface.QueryReader;
import TransactionProcessor.BasicProcessor;

import java.io.IOException;

/**
 * The Main class is the entry point of the program and responsible for initializing components like authentication,
 * query reading, and transaction processing.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // Initialize authentication component
        BasicAuthentication auth = new BasicAuthentication();
        // Initialize query reader
        QueryReader read = new QueryReader();

        // Perform authentication
        String username=auth.authenticate();

        // Read queries from the user
        String queries[] = read.readQueries();

        // Initialize the transaction processor
        BasicProcessor processor = new BasicProcessor(queries,username);

        // The program can continue with further processing as needed.
    }
}
