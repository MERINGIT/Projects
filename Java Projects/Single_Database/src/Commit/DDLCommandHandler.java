package Commit;

import java.io.FileNotFoundException;

/**
 * An interface for handling SQL Data Definition Language (DDL) statements for creating and inserting data.
 */
public interface DDLCommandHandler {
    /**
     * Processes a SQL CREATE statement, creating a table and appending metadata to a CSV file.
     *
     * @param query The SQL CREATE statement to be processed.
     */
    void createStatement(String query);

    /**
     * Processes a SQL INSERT statement, adding data to a table in a CSV file.
     *
     * @param query The SQL INSERT statement to be processed.
     * @throws FileNotFoundException If the CSV file is not found during the insertion operation.
     */
    void insertStatement(String query) throws FileNotFoundException;
}
