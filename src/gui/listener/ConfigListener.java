package gui.listener;

import gui.panel.ConfigPanel;
import service.ConfigService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ConfigListener implements ActionListener {
    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ConfigPanel configPanel = ConfigPanel.instance;
        if (!GUIUtil.checkNumber(configPanel.textFieldBudget, "budget this month")) {
            return;
        }

        String mysqlPath = configPanel.textFieldMysql.getText();
//        if (mysqlPath.length() != 0) {
//            File commandFile = new File(mysqlPath, "bin/mysql.exe");
//            if (!commandFile.exists()) {
//                JOptionPane.showMessageDialog(configPanel, "incorrect mysql path");
//                configPanel.textFieldMysql.grabFocus();
//                return;
//            }
//        }
        if (mysqlPath.length() == 0)
            return;

        ConfigService configService = new ConfigService();
        configService.update(ConfigService.budget, configPanel.textFieldBudget.getText());
        configService.update(ConfigService.mysqlPath, configPanel.textFieldMysql.getText());

        JOptionPane.showMessageDialog(configPanel, "config updated");
    }
}
