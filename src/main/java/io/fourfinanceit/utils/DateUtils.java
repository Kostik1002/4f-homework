package io.fourfinanceit.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static Date addDays(Date date, int days) {
        LocalDate localDate = dateToLocalDate(date).plusDays(days);
        return localDateToDate(localDate);
    }

    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private static Date localDateToDate(LocalDate date) {
        return Date.from(date.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static boolean isTimeBetween(LocalTime time, LocalTime timeAfter, LocalTime timeBefore) {
        return time.isAfter(timeAfter) && time.isBefore(timeBefore);
    }
}
