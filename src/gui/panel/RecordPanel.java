package gui.panel;

import gui.model.CategoryComboBoxModel;
import org.jdesktop.swingx.JXDatePicker;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class RecordPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }
    //single instance
    public static RecordPanel instance = new RecordPanel();

    //declare labels
    JLabel lSpend = new JLabel("expense $");
    JLabel lCategory = new JLabel("category");
    JLabel lComment = new JLabel("comment");
    JLabel lDate = new JLabel("date");

    public JTextField textFieldSpend = new JTextField("0");
    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
    public JComboBox<String> cbCategory = new JComboBox<>(cbModel);
    public JTextField textFieldComment = new JTextField();
    public JXDatePicker datePicker = new JXDatePicker(new Date());
    JButton bSubmit = new JButton("add a record");

    /**
     * constructor
     */
    public RecordPanel() {
        //set components color
        GUIUtil.setColor(ColorUtil.grayColor, lSpend, lCategory, lComment, lDate);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);

        //new panel
        JPanel panelInput = new JPanel();
        JPanel panelSubmit = new JPanel();
        int gap = 40;
        panelInput.setLayout(new GridLayout(4, 2, gap, gap));

        //add components to panel
        panelInput.add(lSpend);
        panelInput.add(textFieldSpend);
        panelInput.add(lCategory);
        panelInput.add(cbCategory);
        panelInput.add(lComment);
        panelInput.add(textFieldComment);
        panelInput.add(lDate);
        panelInput.add(datePicker);

        panelSubmit.add(bSubmit);

        this.setLayout(new BorderLayout());
        this.add(panelInput, BorderLayout.NORTH);
        this.add(panelSubmit, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(RecordPanel.instance);
    }
}
