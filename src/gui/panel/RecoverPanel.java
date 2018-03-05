package gui.panel;

import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;

public class RecoverPanel extends JPanel {
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
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(RecoverPanel.instance);
    }
}
