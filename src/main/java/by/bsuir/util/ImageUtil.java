package by.bsuir.util;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageUtil {

    public static BufferedImage makeGray(BufferedImage image){

        for(int i = 0; i < image.getWidth(); i++){
            for (int j = 0 ; j < image.getHeight(); j ++){
                int rgb = image.getRGB(i, j);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb & 0xFF);

                int grayLevel = (r + g + b) / 3;

                int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;

                image.setRGB(i, j, gray);

            }
        }

        return image;
    }

    public static BufferedImage rotateImage(BufferedImage image, double angle){
        double sin  = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int nw = (int) Math.floor(w * cos + h * sin), nh = (int) Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfig();
        BufferedImage result = gc.createCompatibleImage(nw, nh, Transparency.TRANSLUCENT);
        final Graphics2D g = result.createGraphics();
        g.translate((nw - w)/2, (nh-h) /2);
        g.rotate(angle, w/2, h/2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }

    public static GraphicsConfiguration getDefaultConfig(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }
}
