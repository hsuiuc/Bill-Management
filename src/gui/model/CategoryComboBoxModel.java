package gui.model;

import entity.Category;
import service.CategoryService;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

public class CategoryComboBoxModel implements ComboBoxModel<Category> {

    private CategoryService categoryService = new CategoryService();

    public List<Category> cs = categoryService.list();
    private Category c;

    public CategoryComboBoxModel() {
        if (!cs.isEmpty())
            c = cs.get(0);
    }

    /**
     * Set the selected item. The implementation of this  method should notify
     * all registered <code>ListDataListener</code>s that the contents
     * have changed.
     *
     * @param anItem the list object to select or <code>null</code>
     *               to clear the selection
     */
    @Override
    public void setSelectedItem(Object anItem) {
        c = (Category) anItem;
    }

    /**
     * Returns the selected item
     *
     * @return The selected item or <code>null</code> if there is no selection
     */
    @Override
    public Object getSelectedItem() {
        return cs.isEmpty() ? null : c;
    }

    /**
     * Returns the length of the list.
     *
     * @return the length of the list
     */
    @Override
    public int getSize() {
        return cs.size();
    }

    /**
     * Returns the value at the specified index.
     *
     * @param index the requested index
     * @return the value at <code>index</code>
     */
    @Override
    public Category getElementAt(int index) {
        return cs.get(index);
    }

    /**
     * Adds a listener to the list that's notified each time a change
     * to the data model occurs.
     *
     * @param l the <code>ListDataListener</code> to be added
     */
    @Override
    public void addListDataListener(ListDataListener l) {
        //to do
    }

    /**
     * Removes a listener from the list that's notified each time a
     * change to the data model occurs.
     *
     * @param l the <code>ListDataListener</code> to be removed
     */
    @Override
    public void removeListDataListener(ListDataListener l) {
        //to do
    }

}
