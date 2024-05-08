package Commit;

import java.io.*;
import java.util.*;

/**
 * The SelectOperation class provides methods for selecting and fetching rows from a table in a CSV file.
 */
public class SelectOperation {

    /**
     * Performs a SELECT operation on a table in the CSV file.
     *
     * @param tablename   The name of the table to be queried.
     * @param attributes  The attributes to be selected.
     * @param condition   The conditions to filter rows (optional).
     */
    public void selectOperation(String tablename, String[] attributes, String[] condition) {
        try {
            if (condition.length > 0) {
                fetchRowsFromCSV(tablename, attributes, condition);
            } else {
                fetchAllRowsFromCSV(tablename, attributes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetches rows from a table in the CSV file based on specified attributes and conditions.
     *
     * @param tableName   The name of the table to query.
     * @param attributes  The attributes to be selected.
     * @param condition   The conditions to filter rows.
     * @throws IOException If there is an input/output exception during row retrieval.
     */
    public void fetchRowsFromCSV(String tableName, String[] attributes, String[] condition) throws IOException {
        List<String> resultRows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("database.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Table:" + tableName)) {
                    // If table matches, read the attributes and values
                    String[] attributeNames = line.split(";")[1].split(":")[1].split(",");
                    int count = findRowNumber(line);
                    int i = 0;
                    int c = 2;
                    while (i < count) {
                        String row[] = line.split(";")[c].split("row" + i + ":")[1].split(",");
                        Boolean temp = checkCondition(row, attributeNames, condition);
                        if (temp) {
                            resultRows.add(line.split(";")[c].split("row" + i + ":")[1]);
                        }
                        c++;
                        i++;
                    }
                    // Check if the condition matches
                    for (String attribute : attributeNames) {
                        System.out.print(attribute + "\t\t");
                        // You can perform further processing on each attribute here
                    }
                    System.out.println();
                    for (String row : resultRows) {
                        System.out.println(row + "\t\t\t\t");
                        // You can perform further processing on each attribute here
                    }
                }
            }
        }
    }

    /**
     * Fetches all rows from a table in the CSV file without conditions.
     *
     * @param tableName   The name of the table to query.
     * @param attributes  The attributes to be selected.
     * @throws IOException If there is an input/output exception during row retrieval.
     */
    public void fetchAllRowsFromCSV(String tableName, String[] attributes) throws IOException {
        List<String> resultRows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("database.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Table:" + tableName)) {
                    // If table matches, read the attributes and values
                    String[] attributeNames = line.split(";")[1].split(":")[1].split(",");
                    int count = findRowNumber(line);
                    int i = 0;
                    int c = 2;
                    while (i < count) {
                        resultRows.add(line.split(";")[c].split("row" + i + ":")[1]);
                        c++;
                        i++;
                    }
                    // Check if the condition matches
                    for (String attribute : attributeNames) {
                        System.out.print(attribute + "\t\t");
                        // You can perform further processing on each attribute here
                    }
                    System.out.println();
                    for (String row : resultRows) {
                        System.out.println(row + "\t\t\t\t");
                        // You can perform further processing on each attribute here
                    }
                }
            }
        }
    }

    /**
     * Checks if a row matches the specified condition.
     *
     * @param rowValues      The values in the row to be checked.
     * @param attributeNames The attribute names in the row.
     * @param condition      The condition to be checked.
     * @return True if the row matches the condition, otherwise false.
     */
    public static Boolean checkCondition(String rowValues[], String[] attributeNames, String[] condition ){
        int i=0;
        String attribute=condition[0].split("=")[0];
        String value=condition[0].split("=")[1];
        String result;
        System.out.println();
        Boolean temp=false;
        for (int i1 = 0; i1 < attributeNames.length; i1++) {
            if (attributeNames[i1].trim().equals(attribute) && i1 < rowValues.length) {
                temp= rowValues[i1].trim().equals(value);
            }
            if(temp==true){
                break;

            }
        }
        return temp;

    }
    public static int findRowNumber(String lines){
        int count = 0;
        int lastIndex = 0;
        String subString="row";

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