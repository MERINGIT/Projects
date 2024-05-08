package Commit;

import java.io.IOException;

/**
 * An interface for handling SQL Data Manipulation Language (DML) statements.
 */
public interface DMLCommandHandler {
    /**
     * Handles a SQL DELETE statement, deleting rows from a table.
     *
     * @param query The SQL DELETE statement to be processed.
     */
    void deleteStatement(String query);

    /**
     * Handles a SQL UPDATE statement, updating rows in a table and logging old values.
     *
     * @param query The SQL UPDATE statement to be processed.
     * @throws IOException If there is an input/output exception during the update operation.
     */
    void updateStatement(String query) throws IOException;
}

