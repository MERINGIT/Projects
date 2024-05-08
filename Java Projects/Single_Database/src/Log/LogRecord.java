package Log;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a log record containing information about a database operation.
 */
public class LogRecord {
        private int logSequenceNumber;
        private int transactionId;
        private String operationType = null;
        private String timestamp = null;
        private String databaseObject = null;
        private String oldValue;
        private String newValue;
        private String[] columnname = null;
        private List<String> rowNumber = null;

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        private String username;

        public int getLogSequenceNumber() {
                return logSequenceNumber;
        }

        public void setLogSequenceNumber(int logSequenceNumber) {
                this.logSequenceNumber = logSequenceNumber;
        }

        public int getTransactionId() {
                return transactionId;
        }

        public void setTransactionId(int transactionId) {
                this.transactionId = transactionId;
        }

        public String getOperationType() {
                return operationType;
        }

        public void setOperationType(String operationType) {
                this.operationType = operationType;
        }

        public String getTimestamp() {
                return timestamp;
        }

        public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
        }

        public String getDatabaseObject() {
                return databaseObject;
        }

        public void setDatabaseObject(String databaseObject) {
                this.databaseObject = databaseObject;
        }

        public String getOldValue() {
                return oldValue;
        }

        public void setOldValue(String oldValue) {
                this.oldValue = oldValue;
        }

        public String getNewValue() {
                return newValue;
        }

        public void setNewValue(String newValue) {
                this.newValue = newValue;
        }

        public String[] getColumnname() {
                return columnname;
        }

        public void setColumnname(String[] columnname) {
                this.columnname = columnname;
        }

        public List<String> getRowNumber() {
                return rowNumber;
        }

        public void setRowNumber(List<String> rowNumber) {
                this.rowNumber = rowNumber;
        }


        @Override
        public String toString() {
                return "LogRecord:{" +
                        "logSequenceNumber=" + logSequenceNumber +"|"+
                        ", transactionId=" + transactionId +"|"+
                        ", username='" + username + '\'' +"|"+
                        ", operationType='" + operationType +"|"+
                        ", timestamp='" + timestamp +"|"+
                        ", databaseObject='" + databaseObject +"|"+
                        ", oldValue='" + oldValue +"|"+
                        ", newValue='" + newValue +"|"+
                        ", columnname=" + Arrays.toString(columnname) +"|"+
                        ", rowNumber=" + rowNumber +"|"+
                        '}';
        }



}
