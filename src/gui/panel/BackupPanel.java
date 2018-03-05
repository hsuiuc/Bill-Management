package gui.panel;

import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;

public class BackupPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }

    //single instance
    public static BackupPanel instance = new BackupPanel();

    JButton bBackup = new JButton("backup");

    /**
     * constructor
     */
    public BackupPanel() {
        GUIUtil.setColor(ColorUtil.blueColor, bBackup);
        this.add(bBackup);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(BackupPanel.instance);
    }
}
