package util;

import gui.panel.WorkingPanel;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {

    //stretch rate 0.0-1.0
    private double rate;
    //component to center
    private JComponent c;
    //stretch or not
    private boolean stretch;

    public CenterPanel(double rate, boolean stretch) {
        this.setLayout(null);
        this.rate = rate;
        this.stretch = stretch;
    }

    public CenterPanel(double rate) {
        this(rate, true);
    }

    /**
     * adjust the size of the component, and put it int the center of the container
     */
    public void repaint() {
        if (c != null) {
            Dimension containerSize = this.getSize();
            Dimension componentSize = c.getPreferredSize();

            if (stretch) {
                c.setSize((int) (containerSize.width * rate), (int) (containerSize.height * rate));
            } else {
                c.setSize(componentSize);
            }

            c.setLocation(containerSize.width / 2 - c.getSize().width / 2,
                            containerSize.height / 2 - c.getSize().height / 2);
        }
        super.repaint();
    }

    public void show(JComponent p) {
        this.c = p;
        Component[] cs = getComponents();
        for (Component c : cs) {
            remove(c);
        }
        add(c);

        if (p instanceof WorkingPanel) {
            ((WorkingPanel) p).updateData();
        }

        //updateUI will call repaint
        this.updateUI();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null);

        CenterPanel cp = new CenterPanel(0.65, true);
        frame.setContentPane(cp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        JButton button = new JButton("test");
        cp.show(button);
    }
}
