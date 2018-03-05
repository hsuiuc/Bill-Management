package gui.listener;

import entity.Category;
import gui.panel.CategoryPanel;
import gui.panel.MainPanel;
import gui.panel.RecordPanel;
import gui.panel.SpendPanel;
import service.RecordService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RecordListener implements ActionListener{
    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        RecordPanel recordPanel = RecordPanel.instance;

        if (recordPanel.cbModel.cs.size() == 0) {
            JOptionPane.showMessageDialog(recordPanel, "no category, add category first");
            MainPanel.instance.workingPanel.show(CategoryPanel.instance);
            return;
        }

        if (!GUIUtil.checkZero(recordPanel.textFieldSpend, "expense")) {
            return;
        }

        int spend = Integer.parseInt(recordPanel.textFieldSpend.getText());
        Category category = recordPanel.getSelectedCategory();
        String comment = recordPanel.textFieldComment.getText();
        Date date = recordPanel.datePicker.getDate();

        RecordService recordService = new RecordService();
        recordService.add(spend, category, comment, date);
        JOptionPane.showMessageDialog(recordPanel, "add record successfully");
        MainPanel.instance.workingPanel.show(SpendPanel.instance);
    }
}
