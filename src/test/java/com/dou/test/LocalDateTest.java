package com.dou.test;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class LocalDateTest {

    public static void main(String[] args) {
        LocalDateTime l1 = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime l2 = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        LocalDate ld1 = LocalDate.now().with(DayOfWeek.SUNDAY);

        ZonedDateTime zdt = ZonedDateTime.now();

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
    }
}
