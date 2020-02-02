package com.dou.test.java8;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * @author dsp
 * @date 2019-09-06
 */
public class LocalTimeTest {


    public static void main(String[] args) {
        // localDate();

        // chronoField();

        // localTime();

        // localDateTime();

        Instant instant1 = Instant.ofEpochSecond(3);
        Instant instant2 =  Instant.ofEpochSecond(3, 0);
        Instant instant3 = Instant.ofEpochSecond(2, 1_000_000_000);
        Instant instant4 = Instant.ofEpochSecond(4, -1_000_000_000);

        LocalTime time1 = LocalTime.of(13, 45, 20);
        LocalTime time2 = LocalTime.of(13, 45, 20);
        LocalDateTime dateTime1 = LocalDateTime.now();
        LocalDateTime dateTime2 = LocalDateTime.now();
        Duration d1 = Duration.between(time1, time2);
        Duration d2 = Duration.between(dateTime1, dateTime2);
        Duration d3 = Duration.between(instant1, instant2);

        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes1 = Duration.of(3, ChronoUnit.MINUTES);
        Period tenDays = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);

        // LocalDate date1 = LocalDate.of(2014, 3, 18);
        // LocalDate date2 = date1.withYear(2011);
        // LocalDate date3 = date2.withDayOfMonth(25);
        // LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);

        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.plusWeeks(1);
        LocalDate date3 = date2.minusYears(3);
        LocalDate date4 = date3.plus(6, ChronoUnit.MONTHS);


    }

    private static void localDateTime() {
        LocalDate date = LocalDate.parse("2014-03-18");
        LocalTime time = LocalTime.parse("13:45:20");
        // 2014-03-18T13:45:20
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();

    }

    private static void localTime() {
        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);
    }

    private static void localDate(){
        LocalDate today = LocalDate.now();
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        // 星期
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        // 是否闰年
        boolean leap = date.isLeapYear();
        System.out.println(today);
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
        System.out.println(dow);
        System.out.println(len);
        System.out.println(leap);
    }

    private static void chronoField() {
        LocalDate date = LocalDate.parse("2014-03-18");
        int year = date.get(ChronoField.YEAR);
        int month = date.get(ChronoField.MONTH_OF_YEAR);
        int day = date.get(ChronoField.DAY_OF_MONTH);
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
    }
}
