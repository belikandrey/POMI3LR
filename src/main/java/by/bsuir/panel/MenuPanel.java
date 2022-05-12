package by.bsuir.panel;

import by.bsuir.frame.MainWindow;
import by.bsuir.util.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class MenuPanel extends JPanel {
    private final JButton submit;
    private final JRadioButton isGray;
    private final JTextArea rotation;

    public MenuPanel() {
        setLayout(null);

        submit = new JButton("Submit");
        submit.setSize(100, 50);
        submit.setLocation(80, 200);

        isGray = new JRadioButton("Is Gray");
        isGray.setSize(100, 50);
        isGray.setLocation(30, 10);
        isGray.setSelected(false);


        rotation = new JTextArea("Rotation");
        rotation.setSize(100, 50);
        rotation.setLocation(30, 60);

        isGray.addActionListener(new ButtonListener());
        submit.addActionListener(new ButtonListener());
        add(isGray);
        add(submit);
        add(rotation);



    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == isGray) {
                final boolean selected = isGray.isSelected();
                if (selected) {
                    makeGray();
                } else {
                    makeColor();
                }
            }
            if (e.getSource() == submit){
                final String text = rotation.getText();
                if(text == null || text.isBlank()){
                    //
                    System.out.println("Error");
                    return;
                }
                rotate(Double.parseDouble(text));
            }
        }

        private void makeGray() {
            final MainWindow mainWindow = MainWindow.MAIN_WINDOW;
            final ImagePanel panel = (ImagePanel) mainWindow.getPanel();
            final Image image = panel.getImage();
            BufferedImage bufferedImage = toBufferedImage(image);


            ImageUtil.makeGray(bufferedImage);
            panel.setGrayImage(bufferedImage);
            panel.setShowGray(true);
            panel.updateUI();
            System.out.println("Updated");
        }

        private void rotate(double angle){
            final MainWindow mainWindow = MainWindow.MAIN_WINDOW;
            final ImagePanel panel = (ImagePanel) mainWindow.getPanel();
            final Image image = panel.getImage();
            BufferedImage bufferedImage = toBufferedImage(image);

            BufferedImage newImage = ImageUtil.rotateImage(bufferedImage, angle);
            panel.setImage(newImage);
            panel.updateUI();
            System.out.println("Rotation!");

        }

        private BufferedImage toBufferedImage(Image img) {
            if (img instanceof BufferedImage) {
                return (BufferedImage) img;
            }

            BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);

            final Graphics2D graphics = bufferedImage.createGraphics();
            graphics.drawImage(img, 0, 0, null);
            graphics.dispose();

            return bufferedImage;
        }

        private void makeColor() {
            final MainWindow mainWindow = MainWindow.MAIN_WINDOW;
            final ImagePanel panel = (ImagePanel) mainWindow.getPanel();
            panel.setShowGray(false);
            panel.updateUI();
        }
    }
}
