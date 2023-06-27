package gaussian;
import java.lang.Math;
public class Gaussian {
    float[][] H;
    public Gaussian(int size, double s){
        H = new float[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                H[i][j] = (float) ((float) 1 / (2 * Math.PI * s * s) *
                                        Math.exp(- ( Math.pow(i - size, 2) + Math.pow(j - size, 2) )/ (2 * s * s)));
        }
        }
    }

    public float[][] H() {
        return H;
    }
}
