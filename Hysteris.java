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
                if (image[i][j] > strong) {
                    strongAndWeak[i][j] = 2;
                    continue;
                }
                if (image[i][j] <= strong && image[i][j] > weak) {
                    strongAndWeak[i][j] = 1;
                } else {
                    strongAndWeak[i][j] = 0;
                }
            }
        }
        strongAndWeak = edgeTracking(strongAndWeak);

        int[][] result = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (strongAndWeak[i][j] == 2) {
                    result[i][j] = 128;
                } else {
                    result[i][j] = 0;
                }
            }
        }
        return result;
    }

    /** Perform edge-tracking until an iteration finishes
     * where no new strong edges are marked.
     * @param strongAndWeak
     * @return
     */
    public static int[][] edgeTracking(int[][] strongAndWeak) {
        int numChanges = 0;
        int width = strongAndWeak.length;
        int height = strongAndWeak[0].length;

        //perform edge-tracking
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ArrayList<Integer> neighbors = new ArrayList<Integer>();

                //add all neighboring pixels to arrayList:
                for (int dx = -1; dx < 2; dx++) {
                    for (int dy = -1; dy < 2; dy++) {
                        int indX = Math.min(Math.max(i + dx, 0), width - 1);
                        int indY = Math.min(Math.max(j + dy, 0), height - 1);
                        int neighbor = strongAndWeak[indX][indY];
                        neighbors.add(neighbor);
                    }
                }

                //check if any of the neighbors are strong pixels,
                //if so, mark current pixel as strong
                //otherwise keep it marked as weak in case
                if (strongAndWeak[i][j] == 1) {
                    if (neighbors.contains(2)) {
                        strongAndWeak[i][j] = 2;
                        numChanges += 1;
                    } else {
                        strongAndWeak[i][j] = 1;
                    }
                }
            }
        }

        if (numChanges == 0) {
            return strongAndWeak;
        } else {
            return edgeTracking(strongAndWeak);
        }
    }
}

