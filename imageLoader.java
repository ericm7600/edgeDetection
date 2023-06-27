package edgeDetection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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

    public int[][] getMatrix(){
        BufferedImage greyScale = greyScaleImage();
        //convert grayscale BufferedImage into 1-D array
        byte[] image = ((DataBufferByte) greyScale.getRaster().getDataBuffer()).getData();

        //allocate 2-D array, then populate it with the values from the image
        int[][] out = new int[width][height];
        int row = 0;
        int col = 0;
        for(int i = 0; i < width * height; i++) {
            int pixel = image[i];

            if (pixel < 0) {
                pixel += 256;
            }

            out[col][row] = pixel;
            col++;

            if(col >= width) {
                col = 0;
                row += 1;
            }


        }
        return out;
    }

}

