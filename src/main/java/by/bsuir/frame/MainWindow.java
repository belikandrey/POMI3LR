package by.bsuir.frame;

import by.bsuir.panel.MainPanel;
import org.opencv.core.Core;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    private JPanel panel;

    public static final MainWindow MAIN_WINDOW = new MainWindow();


    private MainWindow() throws HeadlessException {
        super("Lab 3");
        setLayout(null);
        setSize(1080, 590);
        setLocation(145, 100);
        panel = new MainPanel();
        setResizable(true);
        setContentPane(panel);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
