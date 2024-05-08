package Commit;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The DeleteOperation class provides methods to delete rows from a CSV file associated with a specific table.
 */
public class DeleteOperation {
    /**
     * Deletes specified rows from a table in the CSV file.
     *
     * @param tableName   The name of the table from which rows will be deleted.
     * @param rowNumber   The list of row identifiers to be deleted.
     */
    public void deleteOperation(String tableName, List<String> rowNumber) {
        try {
            deleteRowByID(rowNumber, tableName);
            System.out.println("Deleted successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes rows from the CSV file based on row identifiers for a specific table.
     *
     * @param rowNumber The list of row identifiers to be deleted.
     * @param tableName The name of the table from which rows will be deleted.
     * @throws IOException If there is an input/output exception during row deletion.
     */
    public static void deleteRowByID(List<String> rowNumber, String tableName) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("database.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Table:" + tableName)) {
                    String[] attributeNames = line.split(";")[1].split(":")[1].split(",");
                    String[] tempSet = line.split(";");
                    List<String> newArray = new ArrayList<>();

                    for (int i = 2; i < tempSet.length; i++) {
                        boolean found = false;
                        for (String sample : rowNumber) {
                            if (tempSet[i].startsWith(sample)) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            newArray.add(tempSet[i]);
                        }
                    }

                    StringBuilder newLine = new StringBuilder();
                    newLine.append("Table:" + tableName + ";Attributes:");
                    for (String attribute : attributeNames) {
                        newLine.append(attribute + ",");
                    }
                    newLine.deleteCharAt(newLine.length() - 1);
                    newLine.append(";");
                    int i = 0;
                    for (String temp : newArray) {
                        newLine.append("row" + i + ":" + temp.split(":")[1] + ",");
                        i++;
                        newLine.deleteCharAt(newLine.length() - 1);
                        newLine.append(";");
                    }

                    lines.add(newLine.toString());
                } else {
                    lines.add(line);
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("database.csv"))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
