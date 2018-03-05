package service;

import dao.RecordDAO;
import entity.Category;
import entity.Record;

import java.util.Date;

public class RecordService {
    private RecordDAO recordDAO = new RecordDAO();
    public void add(int spend, Category category, String comment, Date date) {
        Record record = new Record(spend, category.getId(), comment, date);
        recordDAO.add(record);
    }
}
