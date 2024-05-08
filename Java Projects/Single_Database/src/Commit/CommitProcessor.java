package Commit;

import Commit.DDLCommands;
import Commit.DMLCommands;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The CommitProcessor class processes and directs different types of SQL queries for execution.
 */
public class CommitProcessor {
    DDLCommands ddl = new DDLCommands();
    DMLCommands dml = new DMLCommands();

    /**
     * Process and execute an array of SQL query statements.
     *
     * @param query An array of SQL query statements to be processed and executed.
     * @throws IOException If there is an input/output exception during query processing.
     */
    public void commitProcessor(String[] query) throws IOException {
        for (String statement : query) {
            if (statement.startsWith("insert")) {
                // Process and execute an INSERT statement.
                ddl.insertStatement(statement);
            } else if (statement.startsWith("create")) {
                // Process and execute a CREATE statement.
                ddl.createStatement(statement);
            }  else if (statement.startsWith("delete")) {
                // Process and execute a DELETE statement.
                dml.deleteStatement(statement);
            } else if (statement.startsWith("update")) {
                // Process and execute an UPDATE statement.
                dml.updateStatement(statement);
            } else {
                // Handle any other statements, if needed.
            }
        }
    }
}
