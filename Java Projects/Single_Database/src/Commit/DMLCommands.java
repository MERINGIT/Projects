package Commit;

import Log.AddingOldValueToLog;
import Log.AddingRowsToLog;
import Log.LogRecord;
import Log.TransactionLog;
import Parser.ParserForDelete;
import Parser.ParserForSelect;
import Parser.ParserForUpdate;
import service.SequenceGenerator;
import service.TableInfo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The DMLCommands class provides methods for handling SQL Data Manipulation Language (DML) statements.
 */
public class DMLCommands implements  DMLCommandHandler{
    LogRecord record;
    SequenceGenerator sequence = new SequenceGenerator();
    ParserForSelect sparser = new ParserForSelect();
    ParserForDelete dparser = new ParserForDelete();
    ParserForUpdate uparser = new ParserForUpdate();
    AddingRowsToLog add = new AddingRowsToLog();
    AddingOldValueToLog upd = new AddingOldValueToLog();
    SelectOperation select = new SelectOperation();
    DeleteOperation delete = new DeleteOperation();
    UpdateOperation update = new UpdateOperation();

    /**
     * Handles a SQL DELETE statement, deleting rows from a table.
     *
     * @param query The SQL DELETE statement to be processed.
     */
    public void deleteStatement(String query) {
        TableInfo info = dparser.parseDeleteCommand(query);
        List<String> result = add.addRowsOnLog(info.getTableName(), info.getAttributes(), info.getConditions());
        delete.deleteOperation(info.getTableName(), result);
    }

    /**
     * Handles a SQL UPDATE statement, updating rows in a table and logging old values.
     *
     * @param query The SQL UPDATE statement to be processed.
     * @throws IOException If there is an input/output exception during the update operation.
     */
    public void updateStatement(String query) throws IOException {
        TableInfo info = uparser.parseUpdateStatement(query);
        String oldValue = upd.addOldValue(info.getTableName(), info.getAttributes(), info.getConditions());
        update.upateOperation(info.getTableName(), info.getAttributes()[0], oldValue, info.getValues());
    }
}
