package com.ens.taskhelper.service;

import com.ens.taskhelper.config.AppConfig;
import com.ens.taskhelper.dto.TvaMeasurementDto;
import com.ens.taskhelper.util.MeasurementDataGenerator;
import com.ens.taskhelper.util.YamlLoader;
import com.ens.taskhelper.writer.excel.ExcelFileWriter;
import com.ens.taskhelper.writer.FileWriterStrategy;
import com.ens.taskhelper.writer.TextFileWriter;

import java.io.File;
import java.util.List;

public class FileGeneratorService {
  private final File targetFile;
  private final List<FileWriterStrategy> writers;
  private final TvaMeasurementDto measurementDto;

  public FileGeneratorService(File targetFile, TvaMeasurementDto measurementDto) {
    AppConfig config = YamlLoader.getConfig();
    List<String> headerLines = config.getForm().getText().getHeader();
    List<String> footerLines = config.getForm().getText().getFooter();

    this.targetFile = targetFile;
    this.measurementDto = measurementDto;

    this.writers = List.of(
        new TextFileWriter(headerLines, footerLines),
        new ExcelFileWriter(headerLines, footerLines)
    );
  }

  public void createFiles() {
    List<String[]> rowData = MeasurementDataGenerator.generate(measurementDto);
    for (FileWriterStrategy writer : writers) {
      writer.write(targetFile, measurementDto, rowData);
    }
  }
}
