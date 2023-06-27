package edgeDetection;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static edgeDetection.Convolution.Convolve;
import static java.awt.image.BufferedImage.TYPE_BYTE_GRAY;

public class testSobel {

    @Test
    public void testSobel() throws InterruptedException {

        String path = "edgeDetection/lizard.jpg";
        imageLoader loader = new imageLoader(path);
        Sobel s = new Sobel();
        int[][] matrix = loader.getMatrix();

        gaussian.Gaussian g = new gaussian.Gaussian(2, 1.4);
        float[][] H = g.H();

        int[][] blurred = Convolve(H, matrix);
        int[][] out = s.getG(blurred);

        int xLength = out.length;
        int yLength = out[0].length;
        BufferedImage b = new BufferedImage(xLength, yLength, TYPE_BYTE_GRAY);

        for(int x = 0; x < xLength; x++) {
            for(int y = 0; y < yLength; y++) {
                int val = Math.abs(out[x][y]);
                if (val >= 256) {
                    val = 255;
                }
                int rgb = new Color(val, val, val).getRGB();
                b.setRGB(x, y, rgb);
            }
        }

        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(b)));

        frame.pack();
        frame.setVisible(true);
        Thread.sleep(5000);

    }
}
