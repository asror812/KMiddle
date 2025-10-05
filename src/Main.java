import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER_NUM = new Scanner(System.in);
    // private static Scanner SCANNER_STR = new Scanner(System.in);
    private static final GenericService genericService = new GenericService();

    public static void main(String[] args) {
        int k = 0;
        while (true) {

            if (k > 0) {
                System.out.println("Invalid input");
            }

            System.out.println("""
                    Choose database
                    1.Волки и собаки
                    2.Ирис
                    3.ABC
                    4.Giper147
                    5.Test""");

            int option0 = SCANNER_NUM.nextInt();
            if (option0 == 1) {
                genericService.read("volkiISobaki");

                int m = getKValue("volkiISobaki");
                if (m == 0)
                    return;

                EuclideanDistance distance = new EuclideanDistance();
                Map<Centroid, List<Record>> fit = Service.fit(GenericService.list, m, distance, 1000);
                for (Map.Entry<Centroid, List<Record>> entry : fit.entrySet()) {
                    Centroid centroid = entry.getKey();
                    List<Double> centroidCoordinates = centroid.coordinates();
                    List<Record> records = entry.getValue();

                    System.out.println("Centroid elements:");
                    for (Double coordinate : centroidCoordinates) {
                        System.out.print(coordinate + " ");
                    }
                    System.out.println(); // New line after printing centroid coordinates

                    System.out.println("Records:");
                    for (Record record : records) {
                        System.out.println(record.features());
                    }
                    System.out.println("----------");
                }
            } else if (option0 == 2) {
                genericService.read("iris");

                int m = getKValue("iris");
                if (m == 0)
                    return;

                EuclideanDistance distance = new EuclideanDistance();
                Map<Centroid, List<Record>> fit = Service.fit(GenericService.list, m, distance, 100000000);

                for (Map.Entry<Centroid, List<Record>> entry : fit.entrySet()) {
                    Centroid centroid = entry.getKey();
                    List<Double> centroidCoordinates = centroid.coordinates();
                    List<Record> records = entry.getValue();

                    System.out.println("Centroid elements:");
                    for (Double coordinate : centroidCoordinates) {
                        System.out.print(coordinate + " ");
                    }
                    System.out.println(); // New line after printing centroid coordinates

                    System.out.println("Records:");
                    for (Record record : records) {
                        System.out.println(record.features());
                    }
                    System.out.println("----------");
                }
            } else if (option0 == 3) {
                genericService.read("abc");
                int m = getKValue("abc");
                if (m == 0)
                    return;

                EuclideanDistance distance = new EuclideanDistance();
                Map<Centroid, List<Record>> fit = Service.fit(GenericService.list, m, distance, 1000);

                for (Map.Entry<Centroid, List<Record>> entry : fit.entrySet()) {
                    Centroid centroid = entry.getKey();
                    List<Double> centroidCoordinates = centroid.coordinates();
                    List<Record> records = entry.getValue();

                    System.out.println("Centroid elements:");
                    for (Double coordinate : centroidCoordinates) {
                        System.out.print(coordinate + " ");
                    }
                    System.out.println(); // New line after printing centroid coordinates

                    System.out.println("Records:");
                    for (Record record : records) {
                        System.out.println(record.features());
                    }
                    System.out.println("----------");
                }
            } else if (option0 == 4) {
                genericService.read("giper147");
                int m = getKValue("giper147");
                if (m == 0)
                    return;

                EuclideanDistance distance = new EuclideanDistance();
                Map<Centroid, List<Record>> fit = Service.fit(GenericService.list, m, distance, 1000);

                for (Map.Entry<Centroid, List<Record>> entry : fit.entrySet()) {
                    Centroid centroid = entry.getKey();
                    List<Double> centroidCoordinates = centroid.coordinates();
                    List<Record> records = entry.getValue();

                    System.out.println("Centroid elements:");
                    for (Double coordinate : centroidCoordinates) {
                        System.out.print(coordinate + "   ");
                    }
                    System.out.println(); // New line after printing centroid coordinates

                    System.out.println("Records:");
                    for (Record record : records) {
                        System.out.println(record.features());
                    }
                    System.out.println("----------");
                }
            } else if (option0 == 5) {
                genericService.read("test");

                int m = getKValue("test");
                if (m == 0)
                    return;

                EuclideanDistance distance = new EuclideanDistance();
                Map<Centroid, List<Record>> fit = Service.fit(GenericService.list, m, distance, 100000000);

                for (Map.Entry<Centroid, List<Record>> entry : fit.entrySet()) {
                    Centroid centroid = entry.getKey();
                    List<Double> centroidCoordinates = centroid.coordinates();
                    List<Record> records = entry.getValue();

                    System.out.println("Centroid elements:");
                    for (Double coordinate : centroidCoordinates) {
                        System.out.print(coordinate + " ");
                    }
                    System.out.println(); // New line after printing centroid coordinates

                    System.out.println("Records:");
                    for (Record record : records) {
                        System.out.println(record.features());
                    }
                    System.out.println("----------");
                }
            } else
                k++;
        }

    }

    private static int getKValue(String db) {

        int k = 0;
        while (true) {

            if (k > 0) {
                System.out.println("Invalid input");
            }

            System.out.println("Напиши значение K");

            int k1 = SCANNER_NUM.nextInt();

            if (k1 != 0) {
                return k1;
            }
            k++;
        }
    }

}
