package com.aof.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date getStartDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTime();
    }

    public static Date getExpireDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        return calendar.getTime();
    }

    public static int getDateDiff(Date startDate, Date endDate) {
        Date targetStartDate = getStartDate(startDate);
        Date targetEndDate = getStartDate(endDate);
        long oneDateMill = 86400000L;
        return (int)((targetEndDate.getTime() - targetStartDate.getTime()) / oneDateMill);
    }

    public static Date dateAdd(Date targDate, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(targDate);
        calendar.add(field, amount);
        return calendar.getTime();
    }
}
