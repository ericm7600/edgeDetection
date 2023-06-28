package edgeDetection;
import gaussian.Gaussian;

import static edgeDetection.Convolution.Convolve;
import static edgeDetection.Otsu.threshold;

public class edgeDetection {
    public imageLoader loader;
    public float[][] H;
    public Sobel sobel;
    public String path;


    public edgeDetection(String path, int k, float s) {
        loader = new imageLoader(path);
        Gaussian gaussian = new Gaussian(k, s);
        H = gaussian.getKernel();
        sobel = new Sobel();
        this.path = path;
    }

    /** Method to perform all of the operations on the image to get the
     * final result.
     *
     * @return
     */
    public int[][] getEdges() {
        int[][] matrix = loader.getMatrix();
        int[][] blurred = Convolve(H, matrix);
        int[][] thinned = sobel.getThinnedEdges(blurred);
        int threshold = threshold(thinned);

        //need method to mark strong and weak edges, then do hysteris
        return null;
    }
}
