package test;

import util.GUIUtil;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        GUIUtil.useLNF();
        JPanel panel = new JPanel();
        panel.add(new JButton("button 1"));
        panel.add(new JButton("button 2"));
        GUIUtil.showPanel(panel);
    }
}
