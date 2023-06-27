package edgeDetection;
import org.junit.Test;
import edgeDetection.Convolution;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static edgeDetection.Convolution.Convolve;
import static java.awt.image.BufferedImage.TYPE_BYTE_GRAY;

public class testConvolution {

    @Test
    public void testConvolution() throws InterruptedException {

        String path = "edgeDetection/lizard.jpg";
        imageLoader loader = new imageLoader(path);
        int[][] matrix = loader.getMatrix();

        gaussian.Gaussian g = new gaussian.Gaussian(10, 1.4);
        float[][] H = g.H();

        int[][] out = Convolve(H, matrix);

        int xLength = out.length;
        int yLength = out[0].length;
        BufferedImage b = new BufferedImage(xLength, yLength, TYPE_BYTE_GRAY);

        for(int x = 0; x < xLength; x++) {
            for(int y = 0; y < yLength; y++) {
                int val = out[x][y];
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
