package Parser;

import service.TableInfo;

import java.util.Arrays;

/**
 * The `ParserForDelete` class is responsible for parsing a `DELETE` SQL command.
 */
public class ParserForDelete {
    /**
     * Parses a `DELETE` SQL command and returns information about the table and conditions.
     *
     * @param deleteQuery The `DELETE` SQL command to parse.
     * @return A `TableInfo` object containing information about the table and conditions.
     */
    public TableInfo parseDeleteCommand(String deleteQuery) {
        String[] parts = deleteQuery.split("\\s+(?i)from\\s+|\\s+(?i)where\\s+");

        if (parts.length < 2) {
            System.out.println("Invalid DELETE query. Must include FROM and optionally WHERE clauses.");
            return null; // Or handle the error as appropriate
        }

        String tableName = parts[1].trim();

        String[] conditions = {};
        if (parts.length > 2) {
            conditions = extractConditions(parts[2]);
        }

        System.out.println("Table Name: " + tableName);
        if (conditions.length > 0) {
            System.out.println("Conditions:");
            Arrays.stream(conditions).forEach(System.out::println);
        }

        return new TableInfo(tableName, null, conditions, null);
    }

    /**
     * Extracts conditions from the WHERE clause.
     *
     * @param conditionPart The WHERE clause of the `DELETE` SQL command.
     * @return An array of conditions.
     */
    private String[] extractConditions(String conditionPart) {
        return Arrays.stream(conditionPart.trim()
                        .split("(?i)\\band\\b|\\bor\\b"))
                .map(String::trim)
                .toArray(String[]::new);
    }
}
