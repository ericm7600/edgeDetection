package gaussian;
import java.lang.Math;
public class Gaussian {
    float[][] H;
    public Gaussian(int k, double s){
        int size = 2 * k + 1;
        H = new float[size][size];

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                H[i - 1][j - 1] = (float) ((float) (1 / (2 * Math.PI * s * s)) *
                                        Math.exp(( Math.pow(i - (k + 1), 2) + Math.pow(j - (k + 1), 2) ) / (- 2 * s * s)));
        }
        }
    }

    public float[][] H() {
        return H;
    }
}
