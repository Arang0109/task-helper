package com.ens.taskhelper.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatter {
  public String dateFormat(LocalDate date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy", Locale.ENGLISH);

    return formatter.format(date).toUpperCase();
  }

  public LocalTime timeParse(String time) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH);

    return LocalTime.parse(time, formatter);
  }
}
