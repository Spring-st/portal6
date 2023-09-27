package com.aof.web.struts.action;

import com.shcnc.struts.action.ActionException;
import com.shcnc.struts.action.ActionUtils;
import com.shcnc.struts.form.DateFormatter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class ActionUtils extends ActionUtils {
    private static DateFormatter dfDisplayDate = new DateFormatter(
            Date.class, "yyyy/MM/dd");

    private static DateFormatter dfDisplayDateTime = new DateFormatter(
            Date.class, "yyyy/MM/dd HH:mm");

    private static DateFormatter df8CharsDate = new DateFormatter(
            Date.class, "yyyyMMdd");

    public static String getTodayAs8Chars() {
        return get8CharsFromDate(new Date());
    }

    public static String getTodayAsDisplayDate() {
        return getDisplayDateFromDate(new Date());
    }

    public static String getDisplayDateFrom8Chars(String date) {
        return String.valueOf(date.substring(0, 4)) + "/" + date.substring(4, 6) + "/" +
                date.substring(6, 8);
    }

    public static String get8CharsFromDisplayDate(String date) {
        return String.valueOf(date.substring(0, 4)) + date.substring(5, 7) +
                date.substring(8, 10);
    }

    public static String get8CharsFromDate(Date date) {
        return df8CharsDate.format(date);
    }

    public static Date getDateFrom8Chars(String s) {
        if (s.length() != 8)
            throw new RuntimeException("date error");
        return (Date)df8CharsDate.unformat(s);
    }

    public static Date getDateFromDisplayDate(String s) {
        if (s.length() != 10)
            throw new RuntimeException("date error");
        return (Date)dfDisplayDate.unformat(s);
    }

    public static String getDisplayDateFromDate(Date date) {
        return dfDisplayDate.format(date);
    }

    public static String getDisplayDateFromDateTime(Date date) {
        return dfDisplayDateTime.format(date);
    }

    public static Date getQueryBeginDateFromDisplayDate(String s) {
        Date date = getDateFromDisplayDate(s);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTime();
    }

    public static Date getQueryToDateFromDisplayDate(String s) {
        Date date = getDateFromDisplayDate(s);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        return calendar.getTime();
    }

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

    public static BigDecimal parseBigDecimal(String amount) {
        if (amount == null)
            return null;
        try {
            return new BigDecimal(amount);
        } catch (Throwable t) {
            return null;
        }
    }

    public static String savaFile(InputStream inputStream) {
        DateFormatter format = new DateFormatter(Date.class,
                "yyyy/MM/dd/");
        String path = format.format(new Date());
        if (emailProperty == null) {
            ActionUtils au = new ActionUtils();
            au.init();
        }
        String path2 = emailProperty.getProperty("path");
        File f = new File(String.valueOf(path2) + path);
        if (!f.exists())
            f.mkdirs();
        format = new DateFormatter(Date.class, "HHmmssSS");
        String name = format.format(new Date());
        try {
            f = new File(String.valueOf(path2) + path + name);
            while (f.exists()) {
                name = format.format(new Date());
                f = new File(String.valueOf(path2) + path + name);
            }
            OutputStream os = new FileOutputStream(f);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1)
                os.write(buffer, 0, bytesRead);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(path) + name;
    }

    public static InputStream getFile(String path) {
        if (emailProperty == null) {
            ActionUtils au = new ActionUtils();
            au.init();
        }
        String path2 = emailProperty.getProperty("path");
        File f = new File(String.valueOf(path2) + path);
        if (!f.exists())
            throw new ActionException(
                    "purchaseOrderAttachment.error.fileSize.zero");
        InputStream in = null;
        try {
            in = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return in;
    }

    private static Properties emailProperty = null;

    public void init() {
        InputStream is = getClass().getResourceAsStream(
                "/file.properties");
        emailProperty = new Properties();
        try {
            emailProperty.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException iOException) {}
        }
    }
}
