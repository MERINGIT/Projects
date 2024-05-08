package Parser;

/**
 * The `InsertCommand` class represents a parsed insert command from an SQL-like query.
 * It encapsulates information about the target table name and the values to be inserted.
 */
public class InsertCommand {
    private String tableName;
    private String values;

    /**
     * Constructs an `InsertCommand` object with the specified table name and values.
     *
     * @param tableName The name of the table to insert values into.
     * @param values    The values to be inserted into the table.
     */
    public InsertCommand(String tableName, String values) {
        this.tableName = tableName;
        this.values = values;
    }

    /**
     * Gets the name of the table to which the values will be inserted.
     *
     * @return The table name.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Gets the values to be inserted into the table.
     *
     * @return The values to be inserted.
     */
    public String getValues() {
        return values;
    }
}
