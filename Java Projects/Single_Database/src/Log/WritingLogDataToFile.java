package Log;

import java.io.FileWriter;
import java.io.IOException;

public class WritingLogDataToFile {
    public void writeToLog(String log) {
        try (FileWriter writer = new FileWriter("log.csv",true)) {
            writer.write(log+";");
            writer.write("\n");
            System.out.println("Data has been written to log.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
