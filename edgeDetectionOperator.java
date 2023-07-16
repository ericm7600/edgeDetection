package edgeDetection;
abstract class edgeDetectionOperator {

    abstract int[][] getGx(int[][] image);
    abstract int[][] getGy(int[][] image);

    public int[][] getG(int[][] image) {
        int[][] Gx = getGx(image);
        int[][] Gy = getGy(image);

        int width = image.length;
        int height = image[0].length;

        int[][] G = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int G_ij = (int) Math.sqrt(Math.pow(Gx[i][j], 2) + Math.pow(Gy[i][j], 2));
                if (G_ij > 255) {
                    G_ij = 255;
                }
                G[i][j] = G_ij;
            }
        }
        return G;
    }

    /** Compute the direction of the image gradient,
     * and assign a number based on the direction that the gradient points:
     * 1: E - W
     * 2: NW - SE
     * 3: N - S
     * 4: NE - SW
     *
     * @param image
     * @return
     */
    public int[][] getTheta(int[][] image) {
        int[][] Gx = getGx(image);
        int[][] Gy = getGy(image);

        int width = image.length;
        int height = image[0].length;

        int[][] theta = new int[width][height];

        double pi = Math.PI;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double T = Math.atan2(Gy[i][j], Gx[i][j]);

                //if the angle is negative, add 180 degrees to make it positive
                if (T < 0) {
                    T += pi;
                }

                //assign the value based on the angle
                if (T <= pi / 8 || T >= 7 * pi / 8) {
                    theta[i][j] = 1;
                }

                if (T > pi / 8 && T <= 3 * pi / 8) {
                    theta[i][j] = 4;
                }

                if (T > 3 * pi / 8 && T <= 5 * pi / 8) {
                    theta[i][j] = 3;
                }
                else {
                    theta[i][j] = 2;
                }

            }
        }
        return theta;
    }

    /** Compare each gradient value with it's neighbors in
     * the direction of the gradient at that point.
     * Suppress the value if either neighbor is larger.
     *
     *
     * @param image
     * @return
     */
    public int[][] getThinnedEdges(int[][] image) {
        int[][] G = getG(image);
        int[][] theta = getTheta(image);

        int width = image.length;
        int height = image[0].length;


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int g = G[i][j];

                switch (theta[i][j]) {
                    case 1:
                        if (!(g > G[Math.max(0, i - 1)][j] && g > G[Math.min(width - 1, i + 1)][j])) {
                            G[i][j] = 0;
                        }
                        break;
                    case 2:
                        if (!(g > G[Math.max(0, i - 1)][Math.max(0, j - 1)] && g > G[Math.min(width - 1, i + 1)][Math.min(height - 1, j + 1)])) {
                            G[i][j] = 0;
                        }
                        break;
                    case 3:
                        if (!(g > G[i][Math.max(0, j - 1)] && g > G[i][Math.min(height - 1, j + 1)])) {
                            G[i][j] = 0;
                        }
                        break;
                    case 4:
                        if (!(g > G[Math.min(width - 1, i + 1)][Math.max(0, j - 1)] && g > G[Math.max(0, i - 1)][Math.min(height - 1, j + 1)])) {
                            G[i][j] = 0;
                        }
                        break;
                }
            }
        }

        return G;
    }
}
