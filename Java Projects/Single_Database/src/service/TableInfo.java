package service;

import java.util.Arrays;

/**
 * The TableInfo class is responsible for storing information about a database table, including its name, attributes, conditions, and values.
 */
public class TableInfo {
    private String tableName;
    private String[] conditions;
    private String[] attributes;
    private String values;

    /**
     * Creates a new TableInfo object with the provided information.
     *
     * @param tableName   The name of the database table.
     * @param attributes  An array of attribute names for the table.
     * @param conditions  An array of conditions that apply to the table.
     * @param values      The values associated with the table.
     */
    public TableInfo(String tableName, String[] attributes, String[] conditions, String values) {
        this.tableName = tableName;
        this.attributes = attributes;
        this.conditions = conditions;
        this.values = values;
    }

    /**
     * Get the name of the database table.
     *
     * @return The name of the table.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Get the array of attribute names for the table.
     *
     * @return An array of attribute names.
     */
    public String[] getAttributes() {
        return attributes;
    }

    /**
     * Get the array of conditions that apply to the table.
     *
     * @return An array of conditions.
     */
    public String[] getConditions() {
        return conditions;
    }

    /**
     * Get the values associated with the table.
     *
     * @return The values associated with the table.
     */
    public String getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "Table Name: " + tableName + ", Attributes: " + Arrays.toString(attributes);
    }
}
