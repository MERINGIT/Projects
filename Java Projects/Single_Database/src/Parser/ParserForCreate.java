package Parser;

import service.TableInfo;

/**
 * The `ParserForCreate` class is responsible for parsing a `CREATE TABLE` SQL command.
 */
public class ParserForCreate {
    /**
     * Parses a `CREATE TABLE` SQL command and returns information about the created table.
     *
     * @param createCommand The `CREATE TABLE` SQL command to parse.
     * @return A `TableInfo` object containing information about the created table.
     */
    public TableInfo parseCreateCommand(String createCommand) {
        CreateTableParser createTableParser = new CreateTableParser();
        TableInfo info = createTableParser.parse(createCommand);
        return info;
    }
}

/**
 * The `CreateTableParser` class is responsible for parsing the details of a created table.
 */
class CreateTableParser {
    /**
     * Parses the column definitions of a `CREATE TABLE` SQL command.
     *
     * @param createCommand The `CREATE TABLE` SQL command to parse.
     * @return A `TableInfo` object containing information about the created table.
     */
    public TableInfo parse(String createCommand) {
        TableInfo info = null;
        String[] parts = createCommand.split("[(]", 2);
        String result = parts[1].substring(0, parts[1].length() - 1);
        parts[1] = result;
        if (parts.length == 2) {
            String tableName = parts[0].replaceAll("create table", " ").trim();
            String columnDefinitions = parts[1].trim();

            // Split the column definitions based on commas and extract column names
            String[] columnNames = getColumnNames(columnDefinitions);
            info = new TableInfo(tableName, columnNames, null, null);
        } else {
            System.out.println("Invalid CREATE TABLE command.");
        }
        return info;
    }

    /**
     * Extracts column names from column definitions.
     *
     * @param columnDefinitions The column definitions to parse.
     * @return An array of column names.
     */
    private static String[] getColumnNames(String columnDefinitions) {
        String[] columnAttributes = columnDefinitions.split(",");
        String[] columnNames = new String[columnAttributes.length];

        for (int i = 0; i < columnAttributes.length; i++) {
            String[] parts = columnAttributes[i].split(" ");
            if (parts.length > 0) {
                columnNames[i] = parts[0];
            }
        }
        return columnNames;
    }
}
