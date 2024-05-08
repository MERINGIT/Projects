package Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The AddingRowsToLog class provides methods for adding rows to a log based on certain conditions.
 */
public class AddingRowsToLog {

    /**
     * Adds rows to a log based on the specified conditions.
     *
     * @param tablename  The name of the table.
     * @param attributes The table's attributes.
     * @param condition  The conditions for selecting rows.
     * @return A list of rows added to the log.
     */
    public List<String> addRowsOnLog(String tablename, String[] attributes, String[] condition) {
        List<String> result = null;
        try {
            if (condition.length > 0) {
                result = deleteRowsInCSV(tablename, attributes, condition);
            } else {
                result = deleteAllRowsFromCSV(tablename, attributes);
            }
            printResult(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deletes rows from the CSV file based on specified conditions and returns the list of deleted rows.
     *
     * @param tableName  The name of the table.
     * @param attributes The table's attributes.
     * @param condition  The conditions for selecting rows.
     * @return A list of deleted rows.
     * @throws IOException If there is an input/output exception during the operation.
     */
    public List<String> deleteRowsInCSV(String tableName, String[] attributes, String[] condition) throws IOException {
        List<String> resultRows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("database.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Table:" + tableName)) {
                    String[] attributeNames = line.split(";")[1].split(":")[1].split(",");
                    int count = findRowNumber(line);
                    int i = 0;
                    int c = 2;
                    while (i < count) {
                        String[] row = line.split(";")[c].split(":")[1].split(",");
                        if (checkCondition(row, attributeNames, condition)) {
                            resultRows.add(line.split(";")[c].split(":")[0]);
                        }
                        c++;
                        i++;
                    }
                }
            }
        }
        return resultRows;
    }

    /**
     * Deletes all rows from the CSV file for the specified table and returns the list of deleted rows.
     *
     * @param tableName  The name of the table.
     * @param attributes The table's attributes.
     * @return A list of deleted rows.
     * @throws IOException If there is an input/output exception during the operation.
     */
    public List<String> deleteAllRowsFromCSV(String tableName, String[] attributes) throws IOException {
        List<String> resultRows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("database.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Table:" + tableName)) {
                    int count = findRowNumber(line);
                    int i = 0;
                    int c = 2;
                    while (i < count) {
                        resultRows.add(line.split(";")[c].split(":")[0]);
                        c++;
                        i++;
                    }
                }
            }
        }
        return resultRows;
    }

    /**
     * Checks if a row's values match the specified conditions.
     *
     * @param rowValues The values in the row to check.
     * @param attributes The attribute names in the row.
     * @param condition  The conditions for selecting rows.
     * @return true if the row matches the conditions; otherwise, false.
     */
    public static boolean checkCondition(String[] rowValues, String[] attributes, String[] condition) {
        String attribute = condition[0].split("=")[0];
        String value = condition[0].split("=")[1];
        for (int i = 0; i < attributes.length && i < rowValues.length; i++) {
            if (attributes[i].trim().equals(attribute) && rowValues[i].trim().equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the number of rows in a line from the CSV file.
     *
     * @param lines The line to analyze.
     * @return The number of rows in the line.
     */
    public static int findRowNumber(String lines) {
        int count = 0;
        int lastIndex = 0;
        String subString = "row";

        while (lastIndex != -1) {
            lastIndex = lines.indexOf(subString, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += subString.length();
            }
        }

        return count;
    }

    private void printResult(List<String> result) {
        for (String str : result) {
            System.out.println(str);
        }
    }
}
