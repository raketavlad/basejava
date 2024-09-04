package com.basejava.webapp.util;

import java.time.LocalDate;
import java.time.Month;

public class DateUtil {

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

    public static void main(String[] args) {
        System.out.println(of(2024, Month.DECEMBER));
    }
}
