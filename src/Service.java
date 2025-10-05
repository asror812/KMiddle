import java.util.*;
import java.util.stream.Collectors;

public class Service {

    public static Map<Centroid, List<Record>> fit(List<Record> records, int k, Distance distance, int maxIterations) {

        List<Centroid> centroids = randomCentroids(records, k);
        Map<Centroid, List<Record>> clusters = new HashMap<>();
        Map<Centroid, List<Record>> lastState = new HashMap<>();

        for (int i = 0; i < maxIterations; i++) {
            boolean isLastIteration = i == maxIterations - 1;

            // Найдем ближащий сентроид для каждой record
            for (Record record : records) {
                Centroid centroid = nearestCentroid(record, centroids, distance);

                assignToCluster(clusters, record, centroid);
            }

            boolean shouldTerminate = isLastIteration || clusters.equals(lastState);

            lastState = clusters;

            if (shouldTerminate) {
                break;
            }

            centroids = relocateCentroids(clusters);

            for (Map.Entry<Centroid, List<Record>> entry : clusters.entrySet()) {
                Centroid centroid = entry.getKey();
                List<Record> records1 = entry.getValue();

                System.out.println("Centroid: ");
                System.out.println("   " + centroid);

                System.out.println("Records: ");
                for (Record record : records1) {
                    System.out.println("  " + record);
                }

                System.out.println();
            }

            clusters = new HashMap<>();
        }
        return lastState;
    }

    private static List<Centroid> randomCentroids(List<Record> records, int k) {

        List<Centroid> centroids = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < k; i++) {
            Record record = records.get(random.nextInt(records.size()));
            centroids.add(new Centroid(record.features()));
        }

        for (Centroid centroid : centroids) {
            System.out.println(centroid);
        }

        return centroids;
    }

    private static Centroid nearestCentroid(Record record, List<Centroid> centroids, Distance distance) {
        double minimumDistance = Double.MAX_VALUE;
        Centroid nearest = null;

        for (Centroid centroid : centroids) {
            double currentDistance = distance.calculate(record.features(), centroid.coordinates());

            if (currentDistance < minimumDistance) {
                minimumDistance = currentDistance;
                nearest = centroid;
            }
        }
        return nearest;
    }

    private static void assignToCluster(Map<Centroid, List<Record>> clusters, Record record, Centroid centroid) {

        clusters.compute(centroid, (key, list) -> {
            if (list == null) {
                list = new ArrayList<>();
            }

            list.add(record);
            return list;
        });
    }

    // Для каждого аттрибута перемещаем центроид в среднее положение всех
    // назначенный точек
    private static Centroid average(Centroid centroid, List<Record> records) {
        if (records == null || records.isEmpty()) {
            return centroid;
        }

        int numDimensions = centroid.coordinates().size();
        List<Double> average = new ArrayList<>(Collections.nCopies(numDimensions, 0.0));

        for (Record record : records) {
            List<Double> features = record.features();
            for (int i = 0; i < features.size(); i++) {
                average.set(i, average.get(i) + features.get(i));
            }
        }

        int numRecords = records.size();
        for (int i = 0; i < numDimensions; i++) {
            average.set(i, average.get(i) / numRecords);
        }

        return new Centroid(average);
    }

    private static List<Centroid> relocateCentroids(Map<Centroid, List<Record>> clusters) {
        return clusters.entrySet().stream()
                .map(entry -> average(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
