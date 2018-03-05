package dao;

import entity.Config;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigDAO {

    public int getTotal() {
        int total = 0;
        try (Connection connection = DBUtil.getConnection(); Statement statement = connection.createStatement();) {
            String sql = "select count(*) from config";
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
     * add a record to table config
     * @param config the record to add
     */
    public void add(Config config) {
        String sql = "insert into config values(null, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, config.getKey());
            preparedStatement.setString(2, config.getValue());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                config.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * update a config record
     * @param config the record to update
     */
    public void update(Config config) {
        String sql = "update config set key_=?, value=? where id=?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, config.getKey());
            preparedStatement.setString(2, config.getValue());
            preparedStatement.setInt(3, config.getId());

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
            String sql = "delete from config where id = " + id;
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * get a record from database, transit it into config object and return
     * @param id the record to retrieve
     * @return the config object
     */
    public Config get(int id) {
        Config config = null;

        try (Connection connection = DBUtil.getConnection(); Statement statement = connection.createStatement();) {
            String sql = "select * from config where id = " + id;
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                String key = resultSet.getString("key_");
                String value = resultSet.getString("value");
                config = new Config(id, key, value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return config;
    }

    /**
     * return a list of records in config table
     * @param start start index
     * @param count num of records to return
     * @return a list of the records
     */
    public List<Config> list(int start, int count) {
        List<Config> configs = new ArrayList<>();
        String sql = "select * from config order by id desc limit ?,?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, count);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String key = resultSet.getString("key_");
                String value = resultSet.getString("value");
                Config config = new Config(id, key, value);

                configs.add(config);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return configs;
    }

    public List<Config> list() {
        return list(0, Short.MAX_VALUE);
    }

    public Config getByKey(String key) {
        Config config = null;
        String sql = "select * from config where key_=?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String value = resultSet.getString("value");
                config = new Config(id, key, value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return config;
    }
}
