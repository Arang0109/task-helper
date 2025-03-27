package com.ens.taskhelper.util;

import com.ens.taskhelper.config.AppConfig;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatter {
  public String dateFormat(LocalDate date) {
    String datePattern = AppConfig.getProperty("pattern.date");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern, Locale.ENGLISH);

    return formatter.format(date).toUpperCase();
  }

  public LocalTime timeParse(String time) {
    System.out.println("time:" + time);
    String timePattern = AppConfig.getProperty("pattern.time");
    System.out.println("pattern.time: " + timePattern);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timePattern, Locale.ENGLISH);

    System.out.println(LocalTime.parse(time, formatter));

    return LocalTime.parse(time, formatter);
  }
}
