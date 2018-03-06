package gui.panel;

import gui.listener.BackupListener;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;

public class BackupPanel extends WorkingPanel {
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
        addListener();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(BackupPanel.instance);
    }

    /**
     * update data on the panel
     */
    @Override
    public void updateData() {

    }

    /**
     * add listener to components on the panel
     */
    @Override
    public void addListener() {
        BackupListener backupListener = new BackupListener();
        bBackup.addActionListener(backupListener);
    }
}
