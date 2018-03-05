package gui.panel;

import javax.swing.*;

public abstract class WorkingPanel extends JPanel {
    /**
     * update data on the panel
     */
    public abstract void updateData();

    /**
     * add listener to components on the panel
     */
    public abstract void addListener();
}
