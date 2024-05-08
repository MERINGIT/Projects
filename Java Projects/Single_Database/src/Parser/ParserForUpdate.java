package Parser;

import service.TableInfo;

import java.util.Arrays;

/**
 * The `ParserForUpdate` class is responsible for parsing an SQL `UPDATE` statement.
 */
public class ParserForUpdate {
    /**
     * Parses an SQL `UPDATE` statement and returns information about the table, attributes, conditions, and new values.
     *
     * @param sql The SQL `UPDATE` statement to parse.
     * @return A `TableInfo` object containing information about the table, attributes, conditions, and new values.
     */
    public TableInfo parseUpdateStatement(String sql) {
        String[] parts = sql.split("\\s+");

        if (parts.length < 6 || !parts[0].equalsIgnoreCase("update") || !parts[2].equalsIgnoreCase("set") || !parts[4].equalsIgnoreCase("where")) {
            System.out.println("Invalid SQL UPDATE statement.");
            return null;
        }

        String tableName = parts[1];
        String attributeName = parts[3].split("=")[0];
        String value = parts[3].split("=")[1];
        String[] conditions = parts[5].trim().split("(?i)\\band\\b|\\bor\\b");

        // Print the extracted information
        System.out.println("Table Name: " + tableName);
        System.out.println("Attribute Name: " + attributeName);
        System.out.println("Value: " + value);
        System.out.println("Conditions:");
        Arrays.stream(conditions).forEach(System.out::println);

        return new TableInfo(tableName, new String[]{attributeName}, conditions, value);
    }
}
