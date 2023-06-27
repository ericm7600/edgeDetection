package edgeDetection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/** This class
 * 1. loads an image from the disk
 * 2. converts the image to black and white
 * 3. converts the image to an INT[][]
 */

public class imageLoader {

    BufferedImage img;

    public imageLoader(String path){
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {

        }
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

        return null;
    }

}

