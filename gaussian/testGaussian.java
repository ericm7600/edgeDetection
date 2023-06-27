package edgeDetection.gaussian;
import org.junit.Test;
import gaussian.Gaussian;
import java.util.Arrays;

public class testGaussian {

    @Test
    public void testGaussian(){
        Gaussian g = new Gaussian(2, 1);
        float[][] H = g.H();

        System.out.println(Arrays.deepToString(H).replace("], ", "]\n"));
    }

}
