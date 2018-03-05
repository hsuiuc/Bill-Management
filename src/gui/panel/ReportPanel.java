package gui.panel;

import util.ChartUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class ReportPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }

    //single instance
    public static ReportPanel instance = new ReportPanel();

    public JLabel label = new JLabel();

    /**
     * constructor
     */
    public ReportPanel() {
        this.setLayout(new BorderLayout());
        Image image = ChartUtil.getImage(600, 400);
        ImageIcon imageIcon = new ImageIcon(image);
        label.setIcon(imageIcon);
        this.add(label);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ReportPanel.instance);
    }
}
