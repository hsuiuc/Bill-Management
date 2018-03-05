package gui.panel;

import gui.listener.ConfigListener;
import service.ConfigService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    //single instance
    public static ConfigPanel instance = new ConfigPanel();

    //components
    JLabel lBudget = new JLabel("budget this month $");
    public JTextField textFieldBudget = new JTextField("0");

    JLabel lMysql = new JLabel("Mysql installation path");
    public JTextField textFieldMysql = new JTextField("");

    JButton bSubmit = new JButton("update");

    /**
     * constructor
     */
    public ConfigPanel() {
        //set components color
        GUIUtil.setColor(ColorUtil.grayColor, lBudget, lMysql);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);

        //new panels
        JPanel panelInput = new JPanel();
        JPanel panelSubmit = new JPanel();

        //set layout
        int gap = 40;
        panelInput.setLayout(new GridLayout(4, 1, gap, gap));

        //add components to panel
        panelInput.add(lBudget);
        panelInput.add(textFieldBudget);
        panelInput.add(lMysql);
        panelInput.add(textFieldMysql);
        panelSubmit.add(bSubmit);

        this.setLayout(new BorderLayout());
        this.add(panelInput, BorderLayout.NORTH);
        this.add(panelSubmit, BorderLayout.CENTER);

        addListener();
    }

    /**
     * update data on the panel
     */
    @Override
    public void updateData() {
        ConfigService configService = new ConfigService();
        String budget = configService.get(ConfigService.budget);
        String mysqlPath = configService.get(ConfigService.mysqlPath);

        textFieldBudget.setText(budget);
        textFieldMysql.setText(mysqlPath);
        textFieldBudget.grabFocus();
    }

    public void addListener() {
        ConfigListener configListener = new ConfigListener();
        bSubmit.addActionListener(configListener);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ConfigPanel.instance);
    }
}
