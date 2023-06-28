package edgeDetection;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;



public class Otsu {


    /** Static method to compute the high-threshold using Otsu's method
     * @param image
     * @return
     */
    public static int threshold(int[][] image) {
        Map<Integer, Integer> histogram = histogram(image);
        double mn = image.length * image[0].length;
        /** Iterate over keys of histogram, considering each key as a threshold value.
         * For each threshold value, compute the fraction of pixels above and below those values (w0 and w1).
         * Then compute the means of each class (u0 and u1).
         * The inter-class variance is then given by w0 * w1 * (u0 - u1) ** 2.
         * Choose the threshold which maximizes inter-class variance.
         */
        Map<Integer, Double> variance = new HashMap<Integer,Double>();

        for (int th : histogram.keySet()) {
            List<Integer> keys = histogram.keySet().stream().collect(Collectors.toList());
            Object[] c0 = keys.stream().filter(i -> i < th).toArray();
            Object[] c1 = keys.stream().filter(i -> i > th).toArray();

            double w0 = 0;
            double u0 = 0;
            for (Object key : c0) {
                w0 += histogram.get((int) key) / mn;
                u0 += histogram.get((int) key) * (int) key / mn;
            }

            double w1 = 0;
            double u1 = 0;
            for (Object key : c1) {
                w1 += histogram.get((int) key) / mn;
                u1 += histogram.get((int) key) * (int) key / mn;
            }

            double var = w0 * w1 * Math.pow(u1 - u0, 2);
            variance.put(th, var);

        }
        return variance.keySet().stream().max(Comparator.comparing(variance::get)).orElse(null);
    }

    /** Helper method to compute the histogram of thinned gradient values.
     * Histogram bins are integers from lowest to brightest values, inclusive.
     * @param image
     * @return
     */
    private static Map<Integer, Integer> histogram(int[][] image) {
        Stream<Integer> values = Arrays.stream(image).flatMapToInt(Arrays::stream).boxed();

        return values.collect(groupingBy(Integer::intValue, Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
    }

}
