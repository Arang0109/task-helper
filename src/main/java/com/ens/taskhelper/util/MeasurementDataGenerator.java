package com.ens.taskhelper.util;

import com.ens.taskhelper.dto.TvaMeasurementDto;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MeasurementDataGenerator {
  public static List<String[]> generate(TvaMeasurementDto dto) {
    List<String[]> rows = new ArrayList<>();

    LocalTime time = dto.getTime();
    double standardValue = dto.getStandardValue();
    double changeAmount = dto.getChangeAmount();
    Random rand = new Random();

    DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm:ss");
    String date = new DateFormatter().dateFormat(dto.getDate());

    for (int i = 0; i < 120; i++) {
      double noise = rand.nextDouble(changeAmount) - changeAmount / 2.0;
      double value = standardValue + noise;
      double rounded = Math.round(value * 10) / 10.0;

      String rValue = (rounded >= 100) ? String.format("%.0f", rounded) : String.format("%.1f", rounded);

      rows.add(new String[] {
          date,
          "  ",
          time.format(timeFmt),
          "     0.0 PPM OK            ",
          rValue, " PPM OK         "
      });

      time = time.plusSeconds(15);
    }

    return rows;
  }
}

