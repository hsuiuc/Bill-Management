package gui.panel;

import gui.listener.RecordListener;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;

public class RecoverPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    //single instance
    public static RecoverPanel instance = new RecoverPanel();

    JButton bRecover = new JButton("recover");

    /**
     * constructor
     */
    public RecoverPanel() {
        GUIUtil.setColor(ColorUtil.blueColor, bRecover);
        this.add(bRecover);

        addListener();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(RecoverPanel.instance);
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
        RecordListener recordListener = new RecordListener();
        bRecover.addActionListener(recordListener);
    }
}
