package edgeDetection;

public class Convolution {

        /** Static class to perform convolution operations
         * A must be a square matrix with an odd number of entries (origin must be in the middle)
         *
         * @param A
         * @param B
         * @return
         */
        public static int[][] Convolve(double[][] A, double[][] B) {
            int width = B.length;
            int height = B[0].length;

            //size of the kernel
            int size = A.length;

            int step = size / 2;

            int[][] out = new int[width][height];

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int accumulator = 0;

                    for (int x = -step; x <= step; x++) {
                        for (int y = -step; y <= step; y++) {
                            int X = i + x;
                            int Y = j + y;

                            //probably a better way to do this...
                            //handling edges by extending edge pixels as far as necessary
                            if (X < 0) { X = 0; }
                            if (X >= width) { X = width - 1; }
                            if (Y < 0) { Y = 0; }
                            if (Y >= height) { Y = height - 1;}

                            accumulator += B[X][Y] * A[x + step][y + step];

                        }
                    }
                    out[i][j] = accumulator;
                }
            }
            return out;
        }
}


