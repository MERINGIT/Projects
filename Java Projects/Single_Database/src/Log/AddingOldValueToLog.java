package Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The AddingOldValueToLog class provides methods for adding the old values to a log based on certain conditions.
 */
public class AddingOldValueToLog {

    /**
     * Adds the old value to a log based on the specified conditions.
     *
     * @param tablename  The name of the table.
     * @param attributes The table's attributes.
     * @param condition  The conditions for selecting old values.
     * @return The old value from the log, or null if not found.
     */
    public String addOldValue(String tablename, String[] attributes, String[] condition) {
        try {
            String result = updateRowsInCSV(tablename, attributes, condition);
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Or handle the error as appropriate
        }
    }

    /**
     * Deletes rows from the CSV file based on specified conditions and returns the old value.
     *
     * @param tableName  The name of the table.
     * @param attributes The table's attributes.
     * @param condition  The conditions for selecting old values.
     * @return The old value from the log, or null if not found.
     * @throws IOException If there is an input/output exception during the operation.
     */
    public String updateRowsInCSV(String tableName, String[] attributes, String[] condition) throws IOException {
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
                        String oldValue = checkCondition(row, attributeNames, condition);
                        if (oldValue != null) {
                            return oldValue;
                        }
                        c++;
                        i++;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Checks if a row's values match the specified conditions and returns the old value.
     *
     * @param rowValues     The values in the row to check.
     * @param attributeNames The attribute names in the row.
     * @param condition     The conditions for selecting old values.
     * @return The old value from the row, or null if not found.
     */
    public String checkCondition(String[] rowValues, String[] attributeNames, String[] condition) {
        String attribute = condition[0].split("=")[0];
        String value = condition[0].split("=")[1];
        for (int i = 0; i < attributeNames.length; i++) {
            if (attributeNames[i].trim().equals(attribute) && i < rowValues.length && rowValues[i].trim().equals(value))
            {
                System.out.println(rowValues[i]);
                return value;
            }
        }
        return null;
    }

    /**
     * Finds the number of rows in a line from the CSV file.
     *
     * @param lines The line to analyze.
     * @return The number of rows in the line.
     */
    public int findRowNumber(String lines) {
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
}
