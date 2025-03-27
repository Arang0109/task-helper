package com.ens.taskhelper.util;

import com.ens.taskhelper.config.AppConfig;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatter {
  private final AppConfig config = YamlLoader.getConfig();

  public String dateFormat(LocalDate date) {
    String datePattern = config.getPattern().getDate();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern, Locale.ENGLISH);

    return formatter.format(date).toUpperCase();
  }

  public LocalTime timeParse(String time) {
    String timePattern = config.getPattern().getTime();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timePattern, Locale.ENGLISH);

    System.out.println(LocalTime.parse(time, formatter));

    return LocalTime.parse(time, formatter);
  }
}
