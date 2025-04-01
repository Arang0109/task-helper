package com.ens.taskhelper.writer;

import com.ens.taskhelper.dto.TvaMeasurementDto;

import java.io.*;
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
        header.stream()
            .map(line -> line + "\n")
            .forEach(line -> writeSafely(writer, line));


        data.stream()
            .map(line -> String.join("", line) + "\n")
            .forEach(line -> writeSafely(writer, line));

        writeSafely(writer, "\n");

        footer.stream()
            .map(line -> line + "\n")
            .forEach(line -> writeSafely(writer, line));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void writeSafely(BufferedWriter writer, String line) {
    try {
      writer.write(line);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

