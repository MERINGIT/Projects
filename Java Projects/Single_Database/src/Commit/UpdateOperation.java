package Commit;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The UpdateOperation class provides methods for updating rows in a table in a CSV file.
 */
public class UpdateOperation {

    /**
     * Performs an UPDATE operation on the specified table in the CSV file.
     *
     * @param TableName   The name of the table to be updated.
     * @param Columnname  The name of the column to be updated.
     * @param OldValue    The old value to be replaced.
     * @param NewValue    The new value to be set.
     * @throws IOException If there is an input/output exception during the update.
     */
    public void upateOperation(String TableName, String Columnname, String OldValue, String NewValue) throws IOException {
        UpdateRowsInCSV(TableName, Columnname, OldValue, NewValue);
        System.out.println("Updated successfully.");
    }

    /**
     * Updates rows in the CSV file for the specified table.
     *
     * @param tableName   The name of the table to update.
     * @param Columnname  The name of the column to update.
     * @param OldValue    The old value to be replaced.
     * @param NewValue    The new value to be set.
     * @throws IOException If there is an input/output exception during the update.
     */
    public void UpdateRowsInCSV(String tableName, String Columnname, String OldValue, String NewValue) throws IOException {
        List<String> resultRows = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        String[] attributeNames;
        List<String> newArray = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("database.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Table:" + tableName)) {
                    // If the table matches, read the attributes and values
                    attributeNames = line.split(";")[1].split(":")[1].split(",");
                    int count = find_row_number(line);
                    int i = 0;
                    int c = 2;
                    while (i < count) {
                        String[] row = line.split(";")[c].split(":")[1].split(",");
                        String[] row1 = checkCondition(row, attributeNames, Columnname, NewValue, OldValue);
                        String temp = String.join(",", row1);
                        System.out.println(temp);
                        newArray.add(temp);
                        c++;
                        i++;
                    }
                    StringBuilder newLine = new StringBuilder();
                    newLine.append("Table:" + tableName + ";Attributes:");
                    for (String attribute : attributeNames) {
                        newLine.append(attribute + ",");
                    }
                    newLine.deleteCharAt(newLine.length() - 1);
                    newLine.append(";");
                    i = 0;
                    for (String temp : newArray) {
                        newLine.append("row" + i + ":" + temp);
                        i++;
                        newLine.append(";");
                    }

                    lines.add(newLine.toString());
                }   // Check if the condition matches

                else {
                    lines.add(line);
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("database.csv"))) {
                for (String line1 : lines) {
                    writer.write(line1);
                    writer.newLine();
                }
            }
        }
    }

    /**
     * Checks and updates a specific row in the CSV file if it matches the condition.
     *
     * @param rowValues     The values in the row to be checked.
     * @param attributeNames The attribute names in the row.
     * @param ColumnName     The name of the column to update.
     * @param NewValue       The new value to set.
     * @param OldValue       The old value to be replaced.
     * @return The updated row values.
     */
    public String[] checkCondition(String[] rowValues, String[] attributeNames, String ColumnName, String NewValue, String OldValue) {
        int i = 0;
        for (int i1 = 0; i1 < attributeNames.length; i1++) {
            if (attributeNames[i1].trim().equals(ColumnName) && i1 < rowValues.length) {
                if (rowValues[i1].trim().equals(OldValue)) {
                    rowValues[i1] = NewValue;
                }
            }
        }
        return rowValues;
    }

    /**
     * Finds the number of rows in a line from the CSV file.
     *
     * @param lines The line to be analyzed.
     * @return The number of rows in the line.
     */
    public static int find_row_number(String lines) {
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
