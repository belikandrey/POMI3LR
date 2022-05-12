package by.bsuir.panel;

import by.bsuir.util.ImageUtil;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private Image image;
    private Image grayImage;
    private boolean showGray;

    public ImagePanel(Image image) {
        this.image = image;
        showGray = false;
        //final Window fullScreenWindow = ImageUtil.getDefaultConfig().getDevice().getFullScreenWindow();
        //setSize(image.getWidth(null) + 200, image.getHeight(null) + 200);
        //setSize(fullScreenWindow.getWidth() - 200, fullScreenWindow.getHeight() - 200);
    }

    @Override
    public void paint(Graphics g) {
        if (showGray && grayImage != null) {
            g.drawImage(grayImage, grayImage.getWidth(null)/2, grayImage.getHeight(null)/2, null);
        } else {
            g.drawImage(image, image.getWidth(null)/2, image.getHeight(null)/2, null);
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getGrayImage() {
        return grayImage;
    }

    public void setGrayImage(Image grayImage) {
        this.grayImage = grayImage;
    }

    public boolean isShowGray() {
        return showGray;
    }

    public void setShowGray(boolean showGray) {
        this.showGray = showGray;
    }
}
