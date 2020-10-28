import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ParseFile implements Runnable {
    private String path;
    private Map<String, HashSet<String>> container;

    public ParseFile(String path, Map<String, HashSet<String>> container) {
        this.path = path;
        this.container = container;
    }

    @Override
    public void run() {
        System.out.println("Чтение файла " + path);
        try(BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String s = "";
            boolean firstIteration = true;
            List<String> headers = new ArrayList<>();
            while ((s = bf.readLine()) != null) {
                String[] elements = s.split(";");
                if (firstIteration) {
                    for (String el : elements) {
                        container.putIfAbsent(el, new HashSet<>());
                        headers.add(el);
                    }
                    firstIteration = false;
                } else {
                    for (int i = 0; i < elements.length; i++) {
                        container.get(headers.get(i)).add(elements[i]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
