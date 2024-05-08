package Log;

import Commit.DDLCommandHandler;
import Parser.InsertCommand;
import Parser.ParserForCreate;
import Parser.ParserForInsert;
import service.SequenceGenerator;
import service.TableInfo;
import service.SequenceGenerator;
import service.TableInfo;

import java.time.LocalDateTime;

/**
 * The DDLCommands class provides methods for handling DDL (Data Definition Language) statements
 * and logging these operations.
 */
public class DDLCommands   {
    private ParserForCreate cparser = new ParserForCreate();
    private ParserForInsert iparser = new ParserForInsert();
    private SequenceGenerator sequence = new SequenceGenerator();
    private WritingLogDataToFile write=new WritingLogDataToFile();

    /**
     * Handles a CREATE statement and logs the operation in a transaction log.
     *
     * @param query            The CREATE statement to process.
     * @param transactionNumber The unique identifier for the current transaction.
     * @param log              The transaction log where the operation is logged.
     */
    public void createStatement(String query, int transactionNumber, TransactionLog log,String username) {
        LogRecord record = new LogRecord();
        TableInfo info = cparser.parseCreateCommand(query);
        record.setLogSequenceNumber(sequence.generateLogSequenceNumber());
        record.setTransactionId(transactionNumber);
        record.setOperationType("CREATE");
        record.setTimestamp(String.valueOf(LocalDateTime.now()));
        record.setDatabaseObject(info.getTableName());
        record.setColumnname(info.getAttributes());
        record.setUsername(username);
        write.writeToLog(record.toString());
        log.addLogRecord(record);
    }

    /**
     * Handles an INSERT statement and logs the operation in a transaction log.
     *
     * @param query            The INSERT statement to process.
     * @param transactionNumber The unique identifier for the current transaction.
     * @param log              The transaction log where the operation is logged.
     */
    public void insertStatement(String query, int transactionNumber, TransactionLog log,String username) {
        LogRecord record = new LogRecord();
        InsertCommand insert = iparser.parseInsertCommand(query);
        String databaseObject = insert.getTableName();
        String newValue = insert.getValues();
        record.setLogSequenceNumber(sequence.generateLogSequenceNumber());
        record.setTransactionId(transactionNumber);
        record.setOperationType("INSERT");
        record.setTimestamp(String.valueOf(LocalDateTime.now()));
        record.setDatabaseObject(databaseObject);
        record.setNewValue(newValue);
        record.setUsername(username);
        write.writeToLog(record.toString());
        log.addLogRecord(record);
    }
}
