import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class Runner {
    public static void run(String[] args) throws FileNotFoundException, InterruptedException {
        ParseArgs parseArgs = new ParseArgs(args);
        HashMap<String, HashSet<String>> container = new LinkedHashMap<>();
        for (String file : parseArgs.getFiles()) {
            ParseFile parseFile = new ParseFile(file, container);
            Thread thread = new Thread(parseFile);
            thread.start();
            thread.join();
        }
        Writer writer = new Writer(container);
        writer.write("src/main/resources/");
    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Runner.run(args);
    }
}
