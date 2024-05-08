package CommandLineInterface;

import java.util.Scanner;

/**
 * The QueryReader class handles reading and parsing transaction queries from the command line.
 */
public class QueryReader {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Reads and parses a sequence of transaction queries from the user input.
     *
     * @return An array of parsed transaction queries.
     */
    public String[] readQueries() {
        StringBuilder codeBuilder = new StringBuilder();

        System.out.println("Enter the transaction============>(follow small letter)");
        System.out.println("Start with begin transaction");
        System.out.println("End with end transaction");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            codeBuilder.append(line);
            if (line.equalsIgnoreCase("end transaction;")) {
                break;
            }
        }

        // Split the transaction queries by semicolon and return as an array.
        return codeBuilder.toString().split(";");
    }
}
