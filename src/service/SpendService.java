package service;

import dao.RecordDAO;
import entity.Record;
import gui.page.SpendPage;
import util.DateUtil;

import java.util.List;

public class SpendService {

    public SpendPage getSpendPage() {
        RecordDAO recordDAO = new RecordDAO();
        List<Record> thisMonthRecords = recordDAO.listThisMonth();
        List<Record> todayRecords = recordDAO.listToday();

        ConfigService configService = new ConfigService();

        int thisMonthTotalDay = DateUtil.thisMonthTotalDay();

        int monthSpend = 0;
        int todaySpend = 0;
        int avgSpendPerDay = 0;
        int monthAvailable = 0;
        int dayAvgAvailable = 0;
        int monthLeftDay = 0;
        int usagePercentage = 0;

        int monthBudget = configService.getIntBudget();

        //calculate expenses this month
        for (Record record : thisMonthRecords) {
            monthSpend += record.getSpend();
        }

        //calculate expenses today
        for (Record record : todayRecords) {
            todaySpend += record.getSpend();
        }

        avgSpendPerDay = monthSpend / thisMonthTotalDay;
        monthAvailable = monthBudget - monthSpend;
        monthLeftDay = DateUtil.thisMonthLeftDay();
        dayAvgAvailable = monthAvailable / monthLeftDay;
        usagePercentage = monthSpend * 100 / monthBudget;

        return new SpendPage(monthSpend, todaySpend, avgSpendPerDay, monthAvailable, dayAvgAvailable, monthLeftDay, usagePercentage);
    }
}
