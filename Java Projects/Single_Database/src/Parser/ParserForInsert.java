package Parser;

public class ParserForInsert {
    public InsertCommand parseInsertCommand(String userInput) {
        InsertValuesParser valuesParser = new InsertValuesParser();
        InsertTableParser tableParser = new InsertTableParser();

        String valuesPart = valuesParser.extractValues(userInput);
        String tableName = tableParser.extractTableName(userInput);

        return new InsertCommand(tableName, valuesPart);
    }
}

class InsertValuesParser {
    public String extractValues(String userInput) {
        // Assuming a simple format: "INSERT INTO tableName VALUES('value1', value2, 'value3')"
        String values = userInput.split("[(]")[1].split("[)]")[0].replaceAll(" ,", ",");
        return values;
    }
}

class InsertTableParser {
    public String extractTableName(String userInput) {
        // Assuming a simple format: "INSERT INTO tableName VALUES('value1', value2, 'value3')"
        String tableName = userInput.split("into")[1].split("values")[0].trim();
        return tableName;
    }
}

