import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class ParseArgs {
    private String[] files;

    public ParseArgs(String[] args) throws FileNotFoundException {
        valid(args);
        this.files = checkDirectory(args);
    }

    public void valid(String[] args) {
        if (args.length == 0)
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
    }

    public String[] getFiles() {
        return files;
    }

    public String[] checkDirectory(String[] args) throws FileNotFoundException {
        if (!Arrays.toString(args).contains("-f")) {
            throw new IllegalArgumentException("-f dont exist");
        }
        String[] files = new String[0];
        //Если -f находится не в нулевой позиции
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-f")) {
                files = args[i + 1].split(",");
                break;
            }
        }
        for (String s : files) {
            if (!Files.exists(Paths.get(s))) {
                throw new FileNotFoundException("Catalog dont exist");
            }
        }
        return files;
    }

}
