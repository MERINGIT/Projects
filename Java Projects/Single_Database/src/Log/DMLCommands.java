package Log;

import Commit.SelectOperation;
import Parser.ParserForDelete;
import Parser.ParserForSelect;
import Parser.ParserForUpdate;
import service.SequenceGenerator;
import service.TableInfo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The DMLCommands class provides methods for handling DML (Data Manipulation Language) statements
 * and logging these operations.
 */
public class DMLCommands {
    LogRecord record;
    SequenceGenerator sequence = new SequenceGenerator();
    ParserForSelect sparser = new ParserForSelect();
    ParserForDelete dparser = new ParserForDelete();
    ParserForUpdate uparser = new ParserForUpdate();
    AddingRowsToLog add = new AddingRowsToLog();
    AddingOldValueToLog update = new AddingOldValueToLog();
    SelectOperation selectOperation = new SelectOperation();
    WritingLogDataToFile write=new WritingLogDataToFile();

    /**
     * Handles a SELECT statement and logs the operation in a transaction log.
     *
     * @param query            The SELECT statement to process.
     * @param transactionNumber The unique identifier for the current transaction.
     * @param log              The transaction log where the operation is logged.
     */
    public void selectStatement(String query, int transactionNumber, TransactionLog log,String username) {
        record = new LogRecord();
        TableInfo info = sparser.parseSelectCommand(query);
        record.setLogSequenceNumber(sequence.generateLogSequenceNumber());
        record.setTransactionId(transactionNumber);
        record.setOperationType("SELECT");
        record.setTimestamp(String.valueOf(LocalDateTime.now()));
        record.setDatabaseObject(info.getTableName());
        record.setColumnname(info.getAttributes());
        record.setUsername(username);
        write.writeToLog(record.toString());
        log.addLogRecord(record);
        selectOperation.selectOperation(info.getTableName(), info.getAttributes(), info.getConditions());
    }

    /**
     * Handles a DELETE statement and logs the operation in a transaction log.
     *
     * @param query            The DELETE statement to process.
     * @param transactionNumber The unique identifier for the current transaction.
     * @param log              The transaction log where the operation is logged.
     */
    public void deleteStatement(String query, int transactionNumber, TransactionLog log,String username) {
        record = new LogRecord();
        TableInfo info = dparser.parseDeleteCommand(query);
        record.setLogSequenceNumber(sequence.generateLogSequenceNumber());
        record.setTransactionId(transactionNumber);
        record.setOperationType("DELETE");
        record.setTimestamp(String.valueOf(LocalDateTime.now()));
        record.setDatabaseObject(info.getTableName());
        record.setColumnname(info.getAttributes());
        List<String> result = add.addRowsOnLog(info.getTableName(), info.getAttributes(), info.getConditions());
        record.setRowNumber(result);
        record.setUsername(username);
        write.writeToLog(record.toString());
        log.addLogRecord(record);
    }

    /**
     * Handles an UPDATE statement and logs the operation in a transaction log.
     *
     * @param query            The UPDATE statement to process.
     * @param transactionNumber The unique identifier for the current transaction.
     * @param log              The transaction log where the operation is logged.
     */
    public void updateStatement(String query, int transactionNumber, TransactionLog log,String username) {
        record = new LogRecord();
        TableInfo info = uparser.parseUpdateStatement(query);
        record.setLogSequenceNumber(sequence.generateLogSequenceNumber());
        record.setTransactionId(transactionNumber);
        record.setOperationType("UPDATE");
        record.setTimestamp(String.valueOf(LocalDateTime.now()));
        record.setDatabaseObject(info.getTableName());
        record.setColumnname(info.getAttributes());
        record.setNewValue(info.getValues());
        record.setColumnname(info.getAttributes());
        record.setOldValue(update.addOldValue(info.getTableName(), info.getAttributes(), info.getConditions()));
        record.setUsername(username);
        write.writeToLog(record.toString());
        log.addLogRecord(record);
    }
}
