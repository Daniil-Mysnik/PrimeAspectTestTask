import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;

public class Writer {
    private Map<String, HashSet<String>> container;

    public Writer(Map<String, HashSet<String>> container) {
        this.container = container;
    }

    public void write(String directoryOut) {
        if (!Files.isDirectory(Paths.get(directoryOut))) {
            throw new RuntimeException("It is not directory!");
        }
        for (String key : container.keySet()) {
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(directoryOut + key + ".csv"))) {
                for (String value : container.get(key)) {
                    bw.write(value + ";");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
