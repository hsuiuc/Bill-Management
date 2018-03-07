package gui.listener;

import gui.panel.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarListener implements ActionListener {
    /**
     * Invoked when an action occurs.
     *
     * @param e event happened
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //get resource button
        MainPanel mainPanel = MainPanel.instance;
        JButton button = (JButton) e.getSource();

        //change to respective panel
        if (button == mainPanel.bReport)
            mainPanel.workingPanel.show(ReportPanel.instance);
        if (button == mainPanel.bCategory)
            mainPanel.workingPanel.show(CategoryPanel.instance);
        if (button == mainPanel.bSpend)
            mainPanel.workingPanel.show(SpendPanel.instance);
        if (button == mainPanel.bRecord)
            mainPanel.workingPanel.show(RecordPanel.instance);
        if (button == mainPanel.bConfig)
            mainPanel.workingPanel.show(ConfigPanel.instance);
        if (button == mainPanel.bBackup)
            mainPanel.workingPanel.show(BackupPanel.instance);
        if (button == mainPanel.bRecover)
            mainPanel.workingPanel.show(RecoverPanel.instance);
    }
}
