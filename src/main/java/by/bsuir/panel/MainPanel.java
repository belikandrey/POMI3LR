package by.bsuir.panel;

import by.bsuir.frame.MainWindow;
import by.bsuir.frame.MenuWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainPanel extends JPanel {
    private final JButton exit;
    //    private final JTextField text;
    private final JButton submit;
    private final JFileChooser fileChooser;

    public MainPanel() {
        setLayout(null);

        exit = new JButton("Exit");
        exit.setSize(100, 50);
        exit.setLocation(900, 400);

//        text = new JTextField("Enter path to photo");
//        text.setSize(200, 50);
//        text.setLocation(300, 250);

        submit = new JButton("Enter file...");
        submit.setSize(100, 50);
        submit.setLocation(550, 250);

        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
//        fileChooser.showOpenDialog(new JPanel());
//        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setSize(100, 50);
        fileChooser.setLocation(300, 350);
        fileChooser.addActionListener(new ButtonListener());

        //add(text);
        add(exit);
        add(submit);
        //add(fileChooser);

        submit.addActionListener(new ButtonListener());
        exit.addActionListener(new ButtonListener());
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == exit) {
                System.exit(0);
            } else if (e.getSource() == submit) {
                fileChooser.showOpenDialog(new JPanel());
            } else if (e.getSource() == fileChooser) {
                final JFileChooser source = (JFileChooser) e.getSource();
                final File selectedFile = source.getSelectedFile();
                if (selectedFile == null || !e.getActionCommand().equals("ApproveSelection")) {
                    return;
                }
                final ImagePanel imagePanel = new ImagePanel(new ImageIcon(selectedFile.getAbsolutePath()).getImage());
                final MainWindow mainWindow = MainWindow.MAIN_WINDOW;
                mainWindow.getPanel().removeAll();
                mainWindow.getPanel().updateUI();
                mainWindow.setPanel(imagePanel);
                mainWindow.setContentPane(mainWindow.getPanel());
                mainWindow.setSize(imagePanel.getImage().getWidth(null), imagePanel.getImage().getHeight(null));

                MenuWindow menuWindow = MenuWindow.MENU_WINDOW;
                menuWindow.setVisible(true);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage image;
        try {
            image = ImageIO.read(new File(getBackgroundPath()));
            g.drawImage(image, 0, 0, this);
        } catch (IOException e) {
            System.out.println("Error : " + e);
        }
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.setColor(Color.WHITE);
        g.drawString("Welcome In Image Editor", 150, 140);
    }

    private String getBackgroundPath() {
        return "src" + File.separator + "main" + File.separator + "resources" + File.separator + "background.jpeg";
    }
}
