import java.util.List;
import java.util.Map;

public class EuclideanDistance  implements Distance{
    @Override
    public double calculate(List<Double> f1, List<Double> f2) {
        double sum = 0;
        for (int i = 0; i < f1.size(); i++) {
            Double v1 = f1.get(i);
            Double v2 = f2.get(i);

            if (v1 != null && v2 != null) {
                sum += Math.pow(v1 - v2, 2);
            }
        }

        return Math.sqrt(sum);
    }
}
