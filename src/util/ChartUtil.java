package util;

import java.awt.*;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.objectplanet.chart.BarChart;
import com.objectplanet.chart.Chart;
import entity.Record;

public class ChartUtil {

    private static String[] sampleLabels(List<Record> recordList) {
        String[] sampleLabels = new String[recordList.size()];

        for (int i = 0; i < sampleLabels.length; i++) {
            if (0 == i % 5)
                sampleLabels[i] = String.valueOf(i + 1 + " day");
        }
        return sampleLabels;
    }

    private static double[] sampleValues(List<Record> recordList) {

        double[] sampleValues = new double[recordList.size()];
        for (int i = 0; i < sampleValues.length; i++) {
            sampleValues[i] = recordList.get(i).getSpend();
        }
        return sampleValues;
    }

    private static int max(double[] sampleValues) {
        int max = 0;
        for (double d : sampleValues) {
            if (d > max) {
                max = (int) d;
            }
        }
        return max;
    }

    public static Image getImage(List<Record> recordList, int width, int height) {
        // 模拟样本数据
        double[] sampleValues = sampleValues(recordList);
        // 下方显示的文字
        String[] sampleLabels = sampleLabels(recordList);
        // 样本中的最大值
        int max = max(sampleValues);

        // 数据颜色
        Color[] sampleColors = new Color[] { ColorUtil.blueColor };

        // 柱状图
        BarChart chart = new BarChart();

        // 设置样本个数
        chart.setSampleCount(sampleValues.length);
        // 设置样本数据
        chart.setSampleValues(0, sampleValues);
        // 设置文字
        chart.setSampleLabels(sampleLabels);
        // 设置样本颜色
        chart.setSampleColors(sampleColors);
        // 设置取值范围
        chart.setRange(0, max * 1.2);
        // 显示背景横线
        chart.setValueLinesOn(true);
        // 显示文字
        chart.setSampleLabelsOn(true);
        // 把文字显示在下方
        chart.setSampleLabelStyle(Chart.BELOW);

        // 样本值的字体
        chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD, 12));
        // 显示图例说明
        chart.setLegendOn(true);
        // 把图例说明放在左侧
        chart.setLegendPosition(Chart.LEFT);
        // 图例说明中的文字
        chart.setLegendLabels(new String[] { "Monthly Expense Report" });
        // 图例说明的字体
        chart.setFont("legendFont", new Font("Dialog", Font.BOLD, 13));
        // 下方文字的字体
        chart.setFont("sampleLabelFont", new Font("Dialog", Font.BOLD, 13));
        // 图表中间背景颜色
        chart.setChartBackground(Color.white);
        // 图表整体背景颜色
        chart.setBackground(ColorUtil.backgroundColor);
        // 把图表转换为Image类型
        Image im = chart.getImage(width, height);
        return im;
    }

    public static void main(String[] args) {
        JPanel p = new JPanel();
        JLabel l = new JLabel();
        Image img = ChartUtil.getImage(null, 800, 300);
        Icon icon = new ImageIcon(img);
        l.setIcon(icon);
        p.add(l);
        GUIUtil.showPanel(p);
    }

}