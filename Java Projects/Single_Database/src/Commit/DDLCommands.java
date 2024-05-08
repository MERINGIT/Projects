package Commit;

import Log.LogRecord;
import Log.TransactionLog;
import Parser.InsertCommand;
import Parser.ParserForCreate;
import Parser.ParserForInsert;
import service.SequenceGenerator;
import service.TableInfo;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

/**
 * The DDLCommands class handles SQL Data Definition Language (DDL) statements for creating and inserting data.
 */
public class DDLCommands implements DDLCommandHandler {
    private ParserForCreate cparser = new ParserForCreate();
    private ParserForInsert iparser = new ParserForInsert();
    private SequenceGenerator sequence = new SequenceGenerator();
    CreateOperation create = new CreateOperation();
    InsertOperation insert = new InsertOperation();

    /**
     * Processes a SQL CREATE statement, creating a table and appending metadata to a CSV file.
     *
     * @param query The SQL CREATE statement to be processed.
     */
    public void createStatement(String query) {
        TableInfo info = cparser.parseCreateCommand(query);
        create.createOperation(info.getAttributes(), info.getTableName());
        System.out.println("Success");
    }

    /**
     * Processes a SQL INSERT statement, adding data to a table in a CSV file.
     *
     * @param query The SQL INSERT statement to be processed.
     * @throws FileNotFoundException If the CSV file is not found during the insertion operation.
     */
    public void insertStatement(String query) throws FileNotFoundException {
        InsertCommand insertTemp = iparser.parseInsertCommand(query);
        insert.insertOperation(insertTemp.getValues(), insertTemp.getTableName());
    }
}
