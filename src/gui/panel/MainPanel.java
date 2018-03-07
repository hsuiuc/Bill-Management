package gui.panel;

import gui.listener.ToolBarListener;
import util.CenterPanel;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.security.PublicKey;

public class MainPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }

    //single instance
    public static MainPanel instance = new MainPanel();

    //public components
    private JToolBar toolBar = new JToolBar();
    public JButton bSpend = new JButton();
    public JButton bRecord = new JButton();
    public JButton bCategory = new JButton();
    public JButton bReport = new JButton();
    public JButton bConfig = new JButton();
    public JButton bBackup = new JButton();
    public JButton bRecover = new JButton();

    public CenterPanel workingPanel;

    /**
     * private constructors because single instance
     */
    private MainPanel() {
        //add icon buttons
        GUIUtil.setImageIcon(bSpend, "home.png", "all expenses");
        GUIUtil.setImageIcon(bRecord, "record.png", "add expense");
        GUIUtil.setImageIcon(bCategory, "category2.png", "category");
        GUIUtil.setImageIcon(bReport, "report.png", "monthly expense report");
        GUIUtil.setImageIcon(bConfig, "config.png", "configuration");
        GUIUtil.setImageIcon(bBackup, "backup.png", "backup");
        GUIUtil.setImageIcon(bRecover, "restore.png", "recover");

        toolBar.add(bSpend);
        toolBar.add(bRecord);
        toolBar.add(bCategory);
        toolBar.add(bReport);
        toolBar.add(bConfig);
        toolBar.add(bBackup);
        toolBar.add(bRecover);

        //set working panel
        workingPanel = new CenterPanel(0.8);

        //set layout
        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH);
        add(workingPanel, BorderLayout.CENTER);

        //add event listener to buttons
        addListener();
    }

    private void addListener() {
        ToolBarListener toolBarListener = new ToolBarListener();

        bSpend.addActionListener(toolBarListener);
        bRecord.addActionListener(toolBarListener);
        bCategory.addActionListener(toolBarListener);
        bReport.addActionListener(toolBarListener);
        bConfig.addActionListener(toolBarListener);
        bBackup.addActionListener(toolBarListener);
        bRecover.addActionListener(toolBarListener);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(MainPanel.instance, 1);
    }
}
