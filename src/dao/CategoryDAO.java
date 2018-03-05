package dao;

import entity.Category;
import entity.Category;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public int getTotal() {
        int total = 0;
        try (Connection connection = DBUtil.getConnection(); Statement statement = connection.createStatement();) {
            String sql = "select count(*) from category";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                total = resultSet.getInt(1);
            }

            System.out.println("total: " + total);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    /**
     * add a record to table category
     * @param category the record to add
     */
    public void add(Category category) {
        String sql = "insert into category values(null, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                category.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * update a category record
     * @param category the record to update
     */
    public void update(Category category) {
        String sql = "update category set name=? where id=?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getId());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete a record
     * @param id the id of the record to delete
     */
    public void delete(int id) {
        try (Connection connection = DBUtil.getConnection(); Statement statement = connection.createStatement();) {
            String sql = "delete from category where id = " + id;
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * get a record from database, transit it into category object and return
     * @param id the record to retrieve
     * @return the category object
     */
    public Category get(int id) {
        Category category = null;

        try (Connection connection = DBUtil.getConnection(); Statement statement = connection.createStatement();) {
            String sql = "select * from category where id = " + id;
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                category = new Category(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    /**
     * return a list of records in category table
     * @param start start index
     * @param count num of records to return
     * @return a list of the records
     */
    public List<Category> list(int start, int count) {
        List<Category> categories = new ArrayList<>();
        String sql = "select * from category order by id desc limit ?,?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, count);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString("name");
                Category category = new Category(id, name);

                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    public List<Category> list() {
        return list(0, Short.MAX_VALUE);
    }
}
