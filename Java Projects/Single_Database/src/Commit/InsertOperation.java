package Commit;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The InsertOperation class provides methods for parsing and writing data to a CSV file for inserting new objects into a table.
 */
public class InsertOperation {
    int lineNumber = 1;

    /**
     * Parses the INSERT command and inserts a new object into a specified table.
     *
     * @param newObject The new object to be inserted into the table.
     * @param tablename The name of the table where the object will be inserted.
     * @throws FileNotFoundException If the CSV file is not found during insertion.
     */
    public void insertOperation(String newObject, String tablename) throws FileNotFoundException {
        writeToCSV(newObject, tablename);
    }

    /**
     * Writes data to the CSV file to insert a new object into a table.
     *
     * @param object    The new object to be inserted into the table.
     * @param tableName The name of the table where the object will be inserted.
     * @throws FileNotFoundException If the CSV file is not found during insertion.
     */
    public void writeToCSV(String object, String tableName) throws FileNotFoundException {
        List<String> resultRows = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        String[] attributeNames;
        List<String> newArray = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("database.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Table:" + tableName)) {
                    // If table matches, read the attributes and values
                    attributeNames = line.split(";")[1].split(":")[1].split(",");
                    int count = findRowNumber(line);
                    int i = 0;
                    int c = 2;
                    while (i < count) {
                        String row = line.split(";")[c];
                        newArray.add(row.split(":")[1]);
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
                    newLine.append("row" + i + ":" + object + ";");

                    lines.add(newLine.toString());
                } // Check if the condition matches
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds the number of rows in a given line.
     *
     * @param line The line containing row information.
     * @return The number of rows found in the line.
     */
    public static int findRowNumber(String line) {
        int count = 0;
        int lastIndex = 0;
        String subString = "row";

        while (lastIndex != -1) {
            lastIndex = line.indexOf(subString, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += subString.length();
            }
        }

        return count;
    }
}
