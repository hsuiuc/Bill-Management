package service;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;

import java.util.Collections;
import java.util.List;

public class CategoryService {
    CategoryDAO categoryDAO = new CategoryDAO();
    RecordDAO recordDAO = new RecordDAO();

    /**
     * list all the categories, desc by records in that category
     * @return list of all categories
     */
    public List<Category> list() {
        List<Category> categoryList = categoryDAO.list();
        for (Category category : categoryList) {
            List<Record> recordList = recordDAO.list(category.getId());
            category.setRecordNumber(recordList.size());
        }
        categoryList.sort((c1, c2) -> (c2.getRecordNumber() - c1.getRecordNumber()));

        return categoryList;
    }

    /**
     * add a new category
     * @param name the name of the category to add
     */
    public void add(String name) {
        Category category = new Category(name);
        categoryDAO.add(category);
    }

    /**
     * update a category
     * @param id id of the category to update
     * @param name name of the category to update
     */
    public void update(int id, String name) {
        Category category = new Category(id, name);
        categoryDAO.update(category);
    }

    /**
     * delete a category
     * @param id the id of the category to delete
     */
    public void delete(int id) {
        categoryDAO.delete(id);
    }
}
