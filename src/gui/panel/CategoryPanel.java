package gui.panel;

import entity.Category;
import gui.listener.CategoryListener;
import gui.model.CategoryTableModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    //single instance
    public static CategoryPanel instance = new CategoryPanel();

    //components
    public JButton bAdd = new JButton("add");
    public JButton bEdit = new JButton("edit");
    public JButton bDelete = new JButton("delete");

    String[] columnNames = new String[]{"category", "records in category"};

    public CategoryTableModel categoryTableModel = new CategoryTableModel();
    public JTable table = new JTable(categoryTableModel);

    /**
     * constructor
     */
    public CategoryPanel() {
        //set components color
        GUIUtil.setColor(ColorUtil.blueColor, bAdd, bEdit, bDelete);

        //add scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        //add submit panel and add buttons to it
        JPanel submit = new JPanel();
        submit.add(bAdd);
        submit.add(bEdit);
        submit.add(bDelete);

        //set layout
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(submit, BorderLayout.SOUTH);

        updateData();

        addListener();
    }

    public void addListener() {
        CategoryListener categoryListener = new CategoryListener();
        bAdd.addActionListener(categoryListener);
        bEdit.addActionListener(categoryListener);
        bDelete.addActionListener(categoryListener);
    }

    /**
     * get the selected category
     * @return selected category
     */
    public Category getSelectedCategory() {
        int index = table.getSelectedRow();
        return categoryTableModel.cs.get(index);
    }

    public void updateData() {
        CategoryService categoryService = new CategoryService();
        categoryTableModel.cs = categoryService.list();
        table.updateUI();
        table.getSelectionModel().setSelectionInterval(0, 0);

        if (categoryTableModel.cs.size() == 0) {
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        } else {
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(CategoryPanel.instance);
    }
}
