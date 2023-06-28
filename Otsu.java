package edgeDetection;
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
        int mn = image.length * image[0].length;
        /** Iterate over keys of histogram, considering each key as a threshold value.
         * For each threshold value, compute the fraction of pixels above and below those values (w0 and w1).
         * Then compute the means of each class (u0 and u1).
         * The inter-class variance is then given by w0 * w1 * (u0 - u1) ** 2.
         * Choose the threshold which maximizes inter-class variance.
         */
        Map<Integer, Double> variance = new HashMap<Integer,Double>();

        for (int th : histogram.keySet()) {
            int count0 = 0;
            int u0 = 0;
            for (int i = 0; i < th; i++) {
                count0 += histogram.get(i);
                u0 += histogram.get(i) * i;
            }

            int count1 = 0;
            int u1 = 0;
            for (int i = th; i <= 255; i++) {
                count1 += histogram.get(i);
                u1 += histogram.get(i) * i;
            }

            u0 = u0 / count0;
            u1 = u1 / count1;
            float w0 = count0 / mn;
            float w1 = 1 - w0;

            double var = w0 * w1 * Math.pow(u0 - u1, 2);
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
        Stream<Integer> values = (Stream<Integer>) Arrays.stream(image).flatMapToInt(Arrays::stream);

        return values.collect(groupingBy(Integer::intValue, Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
    }

}
