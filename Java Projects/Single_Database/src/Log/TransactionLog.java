package Log;

import Commit.CommitProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionLog {
    private List<LogRecord> logRecords;

    public TransactionLog() {
        logRecords = new ArrayList<>();
    }

    public void addLogRecord(LogRecord logRecord) {
        logRecords.add(logRecord);
    }

    public List<LogRecord> getLogRecords() {
        return logRecords;
    }

    public void printLog() {
        for (LogRecord logRecord : logRecords) {
            System.out.println(logRecord.toString());
        }

    }

    public void commit(String[] query, int transactionNumber, TransactionLog log) throws IOException {
        CommitProcessor commit=new CommitProcessor();
        commit.commitProcessor(query);
    }
}
