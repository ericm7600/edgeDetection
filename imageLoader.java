package edgeDetection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

/** This class
 * 1. loads an image from the disk
 * 2. converts the image to black and white
 * 3. converts the image to an INT[][]
 */

public class imageLoader {

    BufferedImage img;
    int width;
    int height;

    public imageLoader(String path){
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {

        }
        width = img.getWidth();
        height = img.getHeight();
    }

    private BufferedImage convertToGrayScale(BufferedImage image){
        BufferedImage result = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                image.TYPE_BYTE_GRAY
        );

        Graphics g = result.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return result;
    }

    public BufferedImage image(){
        return img;
    }

    public BufferedImage greyScaleImage(){
        return convertToGrayScale(img);
    }

    public double[][] getMatrix(){
        BufferedImage greyScale = greyScaleImage();
        //convert grayscale BufferedImage into 1-D array
        byte[] image = ((DataBufferByte) greyScaleImage().getRaster().getDataBuffer()).getData();
        //allocate 2-D array, then populate fill it
        double[][] out = new double[width][height];

        for(int i = 0; i < image.length; i++) {
            out[i % width][i % height] = image[i];
        }
        return out;
    }

}

