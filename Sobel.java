package edgeDetection;
import static edgeDetection.Convolution.Convolve;

public class Sobel extends edgeDetectionOperator{

    final float[][] Sx = {{1, 0, -1},
                        {2, 0, -2},
                        {1, 0 ,-1}};

    final float[][] Sy = {{1, 2, 1},
                        {0, 0, 0},
                        {-1, -2 ,-1}};

    public Sobel() {

    }

    public int[][] getGx(int[][] image) {
        return Convolve(Sx, image);
    }

    public int[][] getGy(int[][] image) {
        return Convolve(Sy, image);
    }
}
