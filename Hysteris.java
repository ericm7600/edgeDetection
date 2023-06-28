package edgeDetection;

import java.util.ArrayList;

public class Hysteris {

    /** Method to mark strong and weak pixels and then perform blob detection.
     * For each weak pixel, if there is a strong pixel adjacent to it (diagonal,
     * or next to) mark the weak pixel as strong. Otherwise suppress it.
     *
     * @param image
     * @param threshold
     * @return
     */
    public static int[][] hysteris(int[][] image, int threshold) {
        int strong = threshold;
        int weak = threshold / 2;
        int width = image.length;
        int height = image[0].length;

        int[][] strongAndWeak = new int[width][height];

        //mark strong and weak pixels
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (image[i][j] >= strong) {
                    strongAndWeak[i][j] = 2;
                    continue;
                }
                if (image[i][j] < strong && image[i][j] >= weak) {
                    strongAndWeak[i][j] = 1;
                } else {
                    strongAndWeak[i][j] = 0;
                }
            }
        }

        //perform edge-tracking
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ArrayList<Integer> neighbors = new ArrayList<Integer>();

                //add all neighboring pixels to arrayList:
                for (int dx = -1; dx < 2; dx++) {
                    for (int dy = -1; dy < 2; dy++) {
                        int indX = Math.min(Math.max(i + dx, 0), width);
                        int indY = Math.min(Math.max(j + dy, 0), height);
                        int neighbor = strongAndWeak[indX][indY];
                        neighbors.add(neighbor);
                    }
                }
                if (neighbors.contains(2)) {
                    strongAndWeak[i][j] = 2;
                } else {
                    strongAndWeak[i][j] = 0;
                }
            }
        }
        int[][] result = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (strongAndWeak[i][j] == 2) {
                    result[i][j] = 255;
                } else {
                    result[i][j] = 0;
                }
            }
        }
        return result;
    }
}
