package com.todo.stealthspace.todo;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Utility {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getDate(LocalDateTime localDate, DateTypeFormat dateTypeFormat) {
        return getDate(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear(), dateTypeFormat);
    }

    public static String getDisplayDate(java.sql.Date dt){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        return getDate(day, month, year, DateTypeFormat.US);
    }

    public static String getDisplayTime(java.sql.Time dt){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int hr = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);

        return getTime(hr, min, TimeTypeFormat.TwelveHour);
    }

    public static String getDate(int day, int month, int year, DateTypeFormat dateTypeFormat) {
        SimpleDateFormat format;
        switch (dateTypeFormat) {
            case UK:
                format = new SimpleDateFormat(Constants.UKFormat);
                break;
            case US:
                format = new SimpleDateFormat(Constants.USFormat);
                break;
            default:
                format = new SimpleDateFormat(Constants.UKFormat);
                break;
        }

        Date date = new Date();
        try {
            date = new SimpleDateFormat("MM/dd/yyyy").parse(String.format("%1$s/%2$s/%3$s", month, day, year));
        } catch (Exception ex) {
            // Log exception
        }
        return format.format(date);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getTime(LocalDateTime localDate, TimeTypeFormat timeTypeFormat) {
        return getTime(localDate.getHour(), localDate.getMinute(), timeTypeFormat);
    }

    public static String getTime(int hourOfDay, int minute, TimeTypeFormat timeTypeFormat) {
        SimpleDateFormat format;
        switch (timeTypeFormat) {
            case TwelveHour:
                format = new SimpleDateFormat(Constants.TwelveHourFormat);
                break;
            case TwentyFourHour:
                format = new SimpleDateFormat(Constants.TwentyFourHourFormat);
                break;
            default:
                format = new SimpleDateFormat(Constants.TwelveHourFormat);
                break;
        }

        Date date = new Date();
        try {
            date = new SimpleDateFormat("HH/mm").parse(String.format("%1$s/%2$s", hourOfDay, minute));
        } catch (Exception ex) {
            // Log exception
        }
        return format.format(date);
    }
}