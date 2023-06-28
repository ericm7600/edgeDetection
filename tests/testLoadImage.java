package edgeDetection;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.awt.image.DataBufferByte;
import java.util.Arrays;


public class testLoadImage {

    @Test
    public void testLoadImage() throws InterruptedException {
        String path = "edgeDetection/lizard.jpg";
        JFrame frame = new JFrame();
        imageLoader loader = new imageLoader(path);

        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(loader.image())));
        frame.pack();
        frame.setVisible(true);
        Thread.sleep(5000);
    }

    @Test
    public void testGreyScale() throws InterruptedException {
        String path = "edgeDetection/lizard.jpg";
        JFrame frame = new JFrame();
        imageLoader loader = new imageLoader(path);

        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(loader.image())));
        frame.getContentPane().add(new JLabel(new ImageIcon(loader.greyScaleImage())));
        frame.pack();
        frame.setVisible(true);
        Thread.sleep(5000);
    }

    @Test
    public void testOutput()  {
        String path = "edgeDetection/lizard.jpg";
        imageLoader loader = new imageLoader(path);
        int[][] out = loader.getMatrix();
        System.out.println(Arrays.deepToString(out).replace("], ", "]\n"));
    }
}
