package dao;

import entity.Record;
import util.DBUtil;
import util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordDAO {

    /**
     * get total number
     * @return total number
     */
    public int getTotal() {
        int total = 0;
        try (Connection connection = DBUtil.getConnection(); Statement statement = connection.createStatement();) {
            String sql = "select count(*) from record";
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
     * add a record
     * @param record the record to add
     */
    public void add(Record record) {
        String sql = "insert into record values(null, ?, ?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            //set params
            preparedStatement.setInt(1, record.getSpend());
            preparedStatement.setInt(2, record.getCid());
            preparedStatement.setString(3, record.getComment());
            preparedStatement.setDate(4, DateUtil.util2sql(record.getDate()));
            
            //execute
            preparedStatement.execute();
            
            //get results
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                record.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * update a record
     * @param record the record to update
     */
    public void update(Record record) {
        String sql = "update record set spend=?, cid=?, comment=?, date=? where id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            //set params
            preparedStatement.setInt(1, record.getSpend());
            preparedStatement.setInt(2, record.getCid());
            preparedStatement.setString(3, record.getComment());
            preparedStatement.setDate(4, DateUtil.util2sql(record.getDate()));

            //execute sql
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
            String sql = "delete from record where id = " + id;
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * get a record object
     * @param id the id of the record
     * @return the record object
     */
    public Record get(int id) {
        Record record = null;

        try (Connection connection = DBUtil.getConnection(); Statement statement = connection.createStatement();) {
            String sql = "select * from record where id = " + id;
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                int spend = resultSet.getInt("spend");
                int cid = resultSet.getInt("cid");
                String comment = resultSet.getString("comment");
                Date date = resultSet.getDate("date");
                
                record = new Record(id, spend, cid, comment, date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return record;
    }

    /**
     * return a list of records from the database
     * @param start start index
     * @param count number
     * @return a list of record objects
     */
    public List<Record> list(int start, int count) {
        List<Record> records = new ArrayList<>();
        String sql = "select * from record order by id desc limit ?,?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, count);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int spend = resultSet.getInt("spend");
                int cid = resultSet.getInt("cid");
                String comment = resultSet.getString("comment");
                Date date = resultSet.getDate("date");

                Record record = new Record(id, spend, cid, comment, date);
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

    public List<Record> list() {
        return list(0, Short.MAX_VALUE);
    }

    /**
     * list all the records in a certain category (has same cid)
     * @param cid the category to return
     * @return a list of records
     */
    public List<Record> list(int cid) {
        List<Record> records = new ArrayList<>();
        String sql = "select * from record where cid = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, cid);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int spend = resultSet.getInt("spend");
                String comment = resultSet.getString("comment");
                Date date = resultSet.getDate("date");

                Record record = new Record(id, spend, cid, comment, date);
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

    /**
     * list all the records in a certain day
     * @param day the day
     * @return a list of record objects
     */
    public List<Record> list(java.util.Date day) {
        List<Record> records = new ArrayList<>();
        String sql = "select * from record where date = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setDate(1, DateUtil.util2sql(day));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int spend = resultSet.getInt("spend");
                int cid = resultSet.getInt("cid");
                String comment = resultSet.getString("comment");


                Record record = new Record(id, spend, cid, comment, day);
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

    /**
     * list all the records in today
     * @return a list of all the record objects in today
     */
    public List<Record> listToday() {
        return list(DateUtil.today());
    }


    /**
     * list all the records from start date to end date
     * @param start start date
     * @param end end date
     * @return a list of record objects
     */
    public List<Record> list(java.util.Date start, java.util.Date end) {
        List<Record> records = new ArrayList<>();

        String sql = "select * from record where date >= ? and date <= ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setDate(1, DateUtil.util2sql(start));
            preparedStatement.setDate(2, DateUtil.util2sql(end));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int spend = resultSet.getInt("spend");
                int cid = resultSet.getInt("cid");
                String comment = resultSet.getString("comment");
                java.util.Date date = resultSet.getDate("date");

                Record record = new Record(id, spend, cid, comment, date);

                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

    public List<Record> listThisMonth() {
        return list(DateUtil.monthBegin(), DateUtil.monthEnd());
    }
}
