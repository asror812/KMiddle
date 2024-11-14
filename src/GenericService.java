import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class GenericService {

    private static final String pathOfVolkiISobaki = "D:\\Java projects\\kNearestNeighbour\\kNearestNeighbour\\VolkiISobaki";
    private static final String pathOfIris = "D:\\Java projects\\kNearestNeighbour\\kNearestNeighbour\\Iris";
    private static final String pathOfABC = "D:\\Java projects\\kNearestNeighbour\\kNearestNeighbour\\ABC";
    private static final String pathOfGiper147 = "D:\\Java projects\\kNearestNeighbour\\kNearestNeighbour\\";
    private static final String pathOfTest = "D:\\Java projects\\kNearestNeighbour\\kMiddle\\Text" ;


    static final List<Record> list = new ArrayList<>();


    public void read(String path) {
        Path p;
        if (path.equals("volkiISobaki")) {
            p = Path.of(pathOfVolkiISobaki);
        } else if (path.equals("iris")) {
            p = Path.of(pathOfIris);
        } else if (path.equals("abc")) {
            p = Path.of(pathOfABC);
        } else if(path.equals((pathOfGiper147))){
            p = Path.of(pathOfGiper147);
        }
        else p = Path.of(pathOfTest);

        try {
            if (Files.exists(p) && Files.size(p) != 0) {
                try (BufferedReader reader = new BufferedReader(new FileReader(p.toFile()))) {
                    String line;

                    while ((line = reader.readLine()) != null) {

                        List<String> split = List.of(line.split("\\s+"));

                        List<Double> nestedList = new ArrayList<>();
                        for (int i = 0; i < split.size(); i++) {
                            nestedList.add(Double.parseDouble(split.get(i)));
                        }


                        Record record = new Record(nestedList);
                        list.add(record);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}