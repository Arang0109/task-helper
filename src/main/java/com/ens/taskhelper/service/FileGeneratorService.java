package com.ens.taskhelper.service;

import com.ens.taskhelper.config.AppConfig;
import com.ens.taskhelper.dto.TvaMeasurementDto;
import com.ens.taskhelper.util.MeasurementDataGenerator;
import com.ens.taskhelper.util.YamlLoader;
import com.ens.taskhelper.writer.ExcelFileWriter;
import com.ens.taskhelper.writer.FileWriterStrategy;
import com.ens.taskhelper.writer.TextFileWriter;

import java.io.File;
import java.util.List;

public class FileGeneratorService {
  private final List<String> headerLines;
  private final List<String> footerLines;
  private final File targetFile;
  private final List<FileWriterStrategy> writers;

  public FileGeneratorService(File targetFile) {
    AppConfig config = YamlLoader.getConfig();
    this.headerLines = config.getForm().getText().getHeader();
    this.footerLines = config.getForm().getText().getFooter();
    this.targetFile = targetFile;

    this.writers = List.of(
        new TextFileWriter(headerLines, footerLines),
        new ExcelFileWriter(headerLines, footerLines)
    );
  }

  public void createFiles(TvaMeasurementDto dto) {
    List<String[]> rowData = MeasurementDataGenerator.generate(dto);
    for (FileWriterStrategy writer : writers) {
      writer.write(targetFile, dto, rowData);
    }
  }
}
