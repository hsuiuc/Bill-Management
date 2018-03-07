package gui.panel;

import gui.page.SpendPage;
import service.SpendService;
import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class SpendPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static SpendPanel instance = new SpendPanel();

    private JLabel lMonthSpend = new JLabel("Spend This Month");
    private JLabel lTodaySpend = new JLabel("Spend Today");
    private JLabel lAvgSpendPerDay = new JLabel("Average Spend Per Day");
    private JLabel lMonthLeft = new JLabel("Money Left This Month");
    private JLabel lDayAvgAvailable = new JLabel("Average Money Available Per Day");
    private JLabel lMonthLeftDay = new JLabel("Days Left This Month");

    private JLabel vMonthSpend = new JLabel("$2300");
    private JLabel vTodaySpend = new JLabel("$25");
    private JLabel vAvgSpendPerDay = new JLabel("$120");
    private JLabel vMonthAvailable = new JLabel("$2084");
    private JLabel vDayAvgAvailable = new JLabel("$389");
    private JLabel vMonthLeftDay = new JLabel("15 Days");

    private CircleProgressBar bar;

    private SpendPanel() {
        this.setLayout(new BorderLayout());
        bar = new CircleProgressBar();
        bar.setBackgroundColor(ColorUtil.blueColor);
        GUIUtil.setColor(ColorUtil.grayColor, lMonthSpend, lTodaySpend, lAvgSpendPerDay, lMonthLeft, lDayAvgAvailable,
                            lMonthLeftDay, vAvgSpendPerDay, vMonthAvailable, vDayAvgAvailable, vMonthLeftDay);
        GUIUtil.setColor(ColorUtil.blueColor, vMonthSpend, vTodaySpend);
        vMonthSpend.setFont(new Font("Microsoft Bold", Font.BOLD, 23));
        vTodaySpend.setFont(new Font("Microsoft Bold", Font.BOLD, 23));

        this.add(center(), BorderLayout.CENTER);
        this.add(south(), BorderLayout.SOUTH);
    }

    private JPanel center() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(west(), BorderLayout.WEST);
        panel.add(center2(), BorderLayout.CENTER);
        return panel;
    }

    private Component west() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(lMonthSpend);
        panel.add(vMonthSpend);
        panel.add(lTodaySpend);
        panel.add(vTodaySpend);
        return panel;
    }

    private Component center2() {
        return bar;
    }

    private JPanel south() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 4));

        panel.add(lAvgSpendPerDay);
        panel.add(lMonthLeft);
        panel.add(lDayAvgAvailable);
        panel.add(lMonthLeftDay);
        panel.add(vAvgSpendPerDay);
        panel.add(vMonthAvailable);
        panel.add(vDayAvgAvailable);
        panel.add(vMonthLeftDay);

        return panel;
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(SpendPanel.instance);
    }

    /**
     * update data on the panel
     */
    @Override
    public void updateData() {
        SpendService spendService = new SpendService();
        SpendPage spend = spendService.getSpendPage();

        //set values
        vMonthSpend.setText(spend.monthSpend);
        vTodaySpend.setText(spend.todaySpend);
        vAvgSpendPerDay.setText(spend.avgSpendPerDay);
        vMonthAvailable.setText(spend.monthAvailable);
        vDayAvgAvailable.setText(spend.dayAvgAvailable);
        vMonthLeftDay.setText(spend.monthLeftDay);
        bar.setProgress(spend.usagePercentage);

        if (spend.isOverSpend) {
            vMonthAvailable.setForeground(ColorUtil.warningColor);
            vMonthSpend.setForeground(ColorUtil.warningColor);
            vTodaySpend.setForeground(ColorUtil.warningColor);

        } else {
            vMonthAvailable.setForeground(ColorUtil.grayColor);
            vMonthSpend.setForeground(ColorUtil.blueColor);
            vTodaySpend.setForeground(ColorUtil.blueColor);
        }
        bar.setForegroundColor(ColorUtil.getByPercentage(spend.usagePercentage));
        addListener();
    }

    /**
     * add listener to components on the panel
     */
    @Override
    public void addListener() {

    }
}
