package com.ens.taskhelper.writer;

import com.ens.taskhelper.dto.TvaMeasurementDto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextFileWriter implements FileWriterStrategy {
  private final List<String> header;
  private final List<String> footer;

  public TextFileWriter(List<String> header, List<String> footer) {
    this.header = header;
    this.footer = footer;
  }

  @Override
  public void write(File targetFile, TvaMeasurementDto dto, List<String[]> data) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile))) {
      for (String line : header) writer.write(line + "\n");

      for (String[] row : data) {
        writer.write(String.join("", row));
        writer.newLine();
      }

      writer.newLine();
      for (String line : footer) writer.write(line + "\n");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

