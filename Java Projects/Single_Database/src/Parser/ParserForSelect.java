package Parser;

import service.TableInfo;

import java.util.Arrays;

/**
 * The `ParserForSelect` class is responsible for parsing a `SELECT` SQL command.
 */
public class ParserForSelect {
    /**
     * Parses a `SELECT` SQL command and returns information about the table, attributes, and conditions.
     *
     * @param selectCommand The `SELECT` SQL command to parse.
     * @return A `TableInfo` object containing information about the table, attributes, and conditions.
     */
    public TableInfo parseSelectCommand(String selectCommand) {
        String[] parts = selectCommand.split("(?i)\\bfrom\\b|\\bwhere\\b");

        if (parts.length < 2) {
            System.out.println("Invalid SELECT query. Must include FROM and optionally WHERE clauses.");
            return null; // Or handle the error as appropriate
        }

        String tableName = parts[1].trim();
        String[] attributeNames = extractAttributeNames(parts[0]);

        String[] conditions = {};
        if (parts.length > 2) {
            conditions = extractConditions(parts[2]);
        }


        return new TableInfo(tableName, attributeNames, conditions, null);
    }

    /**
     * Extracts attribute names from the SELECT clause.
     *
     * @param attributePart The SELECT clause of the `SELECT` SQL command.
     * @return An array of attribute names.
     */
    private String[] extractAttributeNames(String attributePart) {
        String[] attributeNames = attributePart.trim()
                .replaceFirst("(?i)\\bselect\\b", "")
                .split(",");
        return Arrays.stream(attributeNames)
                .map(String::trim)
                .toArray(String[]::new);
    }

    /**
     * Extracts conditions from the WHERE clause.
     *
     * @param conditionPart The WHERE clause of the `SELECT` SQL command.
     * @return An array of conditions.
     */
    private String[] extractConditions(String conditionPart) {
        return Arrays.stream(conditionPart.trim()
                        .split("(?i)\\band\\b|\\bor\\b"))
                .map(String::trim)
                .toArray(String[]::new);
    }
}
