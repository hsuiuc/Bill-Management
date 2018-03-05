package util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    static long milisecondsOfOneDay = 1000 * 60 * 60 * 24;

    public static java.sql.Date util2sql(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * get today, set hour, minute and second to 0
     * @return today
     */
    public static Date today() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * get beginning of the month, set hout, minute, second and mili to 0
     * used for statistics
     * @return beginning of the month
     */
    public static Date monthBegin() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DATE, 1);

        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    /**
     * get end of this month
     * @return end of month
     */
    public static Date monthEnd() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, -1);

        return c.getTime();
    }

    /**
     * total days of this month
     * @return total days of this month
     */
    public static int thisMonthTotalDay() {
        long lastDayMili = monthEnd().getTime();
        long firstDayMili = monthBegin().getTime();

        return (int) ((lastDayMili - firstDayMili) / milisecondsOfOneDay) + 1;
    }

    /**
     * days left in this month
     * @return days left in this month
     */
    public static int thisMonthLeftDay() {
        long lastDayMili = monthEnd().getTime();
        long todayMili = today().getTime();

        return (int) ((lastDayMili - todayMili) / milisecondsOfOneDay) + 1;
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.monthEnd());
    }
}
