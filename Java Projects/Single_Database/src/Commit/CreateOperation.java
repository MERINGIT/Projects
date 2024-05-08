package Commit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The CreateOperation class handles creating and appending data to a CSV file.
 */
class CreateOperation {
    /**
     * Creates and appends data to a CSV file for a specific table.
     *
     * @param attributes An array of attribute values to be added to the CSV file.
     * @param tableName  The name of the table to which the data belongs.
     */
    public void createOperation(String[] attributes, String tableName) {
        appendToCSVFile("database.csv", attributes, tableName);
    }

    /**
     * Appends data to a CSV file.
     *
     * @param filePath   The path of the CSV file to which data should be appended.
     * @param attributes An array of attribute values to be added to the CSV file.
     * @param tableName  The name of the table to which the data belongs.
     */
    private void appendToCSVFile(String filePath, String[] attributes, String tableName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("\n");
            writer.write("Table:" + tableName + ";");
            String result = String.join(", ", attributes);
            writer.write("Attributes:" + result);
            writer.write(";");
        } catch (IOException e) {
            System.err.println("Error appending to CSV file: " + e.getMessage());
        }
    }
}
