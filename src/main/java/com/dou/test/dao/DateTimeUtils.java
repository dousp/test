package com.dou.test.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;

/**
 * @author Jalon
 * @Description: use LocalDate LocalTime LocalDateTime DateTimeFormatter to replace SimpleDateFormat
 * @since jdk1.8 
 * @create 2018/8/30 - 13:25
 * @package com.ikang.saas.common.utils
 */
public class DateTimeUtils {
    private static final Log LOGGER = LogFactory.getLog(DateTimeUtils.class);
    public static final String TIME_PATTERN = "HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    public static final DateTimeFormatter DATETIME_FORMATTER =  DateTimeFormatter.ofPattern(DATETIME_PATTERN);

    /**
     * currentLocalDate
     * @return
     */
    public static LocalDate currentLocalDate() {
        return LocalDate.now();
    }

    /**
     * currentLocalTime
     * @return
     */
    public static LocalTime currentLocalTime() {
        return LocalTime.now();
    }

    /**
     * currentLocalDateTime
     * @return
     */
    public static LocalDateTime currentLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * currentDate
     * @return
     */
    public static String currentDate() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String currentDateTime() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }

    /**
     * HH:mm:ss
     * @return
     */
    public static String currentTime() {
        return LocalTime.now().format(TIME_FORMATTER);
    }

    public static LocalDate parseLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    public static LocalDateTime parseLocalDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER);
    }

    public static LocalTime parseLocalTime(String timeStr) {
        return LocalTime.parse(timeStr, TIME_FORMATTER);
    }

    public static String formatLocalDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    public static String formatLocalDateTime(LocalDateTime datetime) {
        return datetime.format(DATETIME_FORMATTER);
    }

    public static String formatLocalTime(LocalTime time) {
        return time.format(TIME_FORMATTER);
    }

    public static Date parseDate(String dateStr) {
        LocalDateTime dateTime = parseLocalDateTime(dateStr);
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    public static String formateDate(Date date) {
        return date == null ? "" : date.toInstant().atZone(ZoneId.systemDefault()).format(DATETIME_FORMATTER);
    }

    public static String formatDateYMD(Date date) {
        return date == null ? "" : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(DATE_FORMATTER);
    }

    public static String formatDateHMS(Date date) {
        return date == null ? "" : date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime().format(TIME_FORMATTER);
    }

    public static String plusDayStr(Date date, int n) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate newLocalDate = localDate.plusDays(n);
        return newLocalDate.format(DATE_FORMATTER);
    }

    // public static Date plusDay(Date date, int days) {
    //     if (!StringUtils.hasEmpty(date, days)) {
    //         LocalDateTime dateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    //         LocalDateTime newDateTime = dateTime.plus(days, ChronoUnit.DAYS);
    //         return Date.from(newDateTime.atZone(ZoneId.systemDefault()).toInstant());
    //     }
    //     return null;
    // }

    public static Date startOfDay(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MIN);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date endOfDay(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MAX);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 日期相隔
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static Duration durations(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive);
    }

    public static int periodDays(LocalDate startDateInclusive, LocalDate endDateExclusive) {
        return Period.between(startDateInclusive, endDateExclusive).getDays();
    }

    public static long durationHours(Temporal startInclusive, Temporal endExclusive) {
        return durations(startInclusive, endExclusive).toHours();
    }

    public static long durationMinutes(Temporal startInclusive, Temporal endExclusive) {
        return durations(startInclusive, endExclusive).toMinutes();
    }

    public static long durationMillis(Temporal startInclusive, Temporal endExclusive) {
        return durations(startInclusive, endExclusive).toMillis();
    }

    /**
     * 获取年，月，日
     */
    public static int currentYear() {
        return LocalDate.now().getYear();
    }
    public static int currentMonth() {
        return LocalDate.now().getMonthValue();
    }
    public static int currentDay() {
        return LocalDate.now().getDayOfMonth();
    }
    public static java.sql.Date toIosDate(Date date) {
        if(date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }
    public static void main(String[] args) {
        System.out.println(parseLocalTime("12:12:00"));
    }
}
