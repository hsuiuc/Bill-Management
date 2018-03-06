package gui.panel;

import entity.Record;
import service.ReportService;
import util.ChartUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReportPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    //single instance
    public static ReportPanel instance = new ReportPanel();

    public JLabel label = new JLabel();

    /**
     * constructor
     */
    public ReportPanel() {
        this.setLayout(new BorderLayout());
        ReportService reportService = new ReportService();
        List<Record> recordList = reportService.listThisMonthRecords();
        Image image = ChartUtil.getImage(recordList, 600, 400);
        ImageIcon imageIcon = new ImageIcon(image);
        label.setIcon(imageIcon);
        this.add(label);
        addListener();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ReportPanel.instance);
    }

    /**
     * update data on the panel
     */
    @Override
    public void updateData() {
        ReportService reportService = new ReportService();
        List<Record> recordList = reportService.listThisMonthRecords();
        Image image = ChartUtil.getImage(recordList, 600, 400);
        ImageIcon imageIcon = new ImageIcon(image);
        label.setIcon(imageIcon);
    }

    /**
     * add listener to components on the panel
     */
    @Override
    public void addListener() {

    }
}
