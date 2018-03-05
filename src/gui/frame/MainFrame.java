package gui.frame;

import gui.panel.MainPanel;

import javax.swing.*;

public class MainFrame extends JFrame{
    //single instance
    public static MainFrame instance = new MainFrame();

    /**
     * constructor
     */
    private MainFrame() {
        this.setSize(1600, 900);
        this.setTitle("Bill Management");
        this.setContentPane(MainPanel.instance);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        instance.setVisible(true);
    }
}
