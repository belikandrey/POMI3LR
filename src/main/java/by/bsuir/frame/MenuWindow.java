package by.bsuir.frame;

import by.bsuir.panel.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class MenuWindow extends JFrame {

    private JPanel panel;

    public static final MenuWindow MENU_WINDOW = new MenuWindow();


    private MenuWindow() throws HeadlessException {
        super("Menu");
        setLayout(null);
        setSize(250, 300);
        setLocation(700, 150);
        panel = new MenuPanel();
        setResizable(false);
        setContentPane(panel);
        setLayout(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
