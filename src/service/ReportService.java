package service;

import dao.RecordDAO;
import entity.Record;
import util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportService {

    /**
     * get total spend of a certain day
     * @param date date
     * @param monthRawData a list of records of the month
     * @return total spend of the date
     */
    public int getDaySpend(Date date, List<Record> monthRawData) {
        int daySpend = 0;
        for (Record record : monthRawData) {
            if (record.getDate().equals(date)) {
                daySpend += record.getSpend();
            }
        }
        return daySpend;
    }

    public List<Record> listThisMonthRecords() {
        RecordDAO recordDAO = new RecordDAO();
        List<Record> monthRawData = recordDAO.listThisMonth();

        List<Record> result = new ArrayList<>();

        Date monthBegin = DateUtil.monthBegin();
        int monthTotalDay = DateUtil.thisMonthTotalDay();
        Calendar c = Calendar.getInstance();

        for (int i = 0; i < monthTotalDay; i++) {
            c.setTime(monthBegin);
            c.add(Calendar.DATE, i);
            Date eachDayOfThisMonth = new Date(c.getTime().getYear(), c.getTime().getMonth(), c.getTime().getDate());
            int daySpend = getDaySpend(eachDayOfThisMonth, monthRawData);
            Record record = new Record();
            record.setSpend(daySpend);
            System.out.println(record);
            result.add(record);
        }
        return result;
    }
}
