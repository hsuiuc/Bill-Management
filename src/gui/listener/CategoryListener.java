package gui.listener;

import entity.Category;
import gui.panel.CategoryPanel;
import service.CategoryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryListener implements ActionListener {
    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryPanel categoryPanel = CategoryPanel.instance;

        JButton button = (JButton) e.getSource();

        //add
        if (button == categoryPanel.bAdd) {
            String name = JOptionPane.showInputDialog(null);
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(categoryPanel, "name shouldn't be empty");
                return;
            }
            CategoryService categoryService = new CategoryService();
            categoryService.add(name);
        }

        //edit
        if (button == categoryPanel.bEdit) {
            Category category = categoryPanel.getSelectedCategory();
            int id = category.getId();
            String name = JOptionPane.showInputDialog("edit category name", category.getName());
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(categoryPanel, "name shouldn't be empty");
                return;
            }

            CategoryService categoryService = new CategoryService();
            categoryService.update(id, name);
        }

        //delete
        if (button == categoryPanel.bDelete) {
            Category category = categoryPanel.getSelectedCategory();
            if (category.getRecordNumber() != 0) {
                JOptionPane.showMessageDialog(categoryPanel, "there are records in this category, can't delete");
                return;
            }
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(categoryPanel, "confirm to delete")) {
                return;
            }

            int id = category.getId();
            CategoryService categoryService = new CategoryService();
            categoryService.delete(id);
        }

        //update data
        categoryPanel.updateData();
    }
}
