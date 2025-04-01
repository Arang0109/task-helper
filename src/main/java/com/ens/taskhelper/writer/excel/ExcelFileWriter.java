package com.ens.taskhelper.writer.excel;

import com.ens.taskhelper.dto.TvaMeasurementDto;
import com.ens.taskhelper.util.FilePathResolver;
import com.ens.taskhelper.writer.FileWriterStrategy;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelFileWriter implements FileWriterStrategy {
  private final WorkbookFactory workbookFactory;
  private final TitleGenerator titleGenerator;
  private final ResultGenerator resultGenerator;

  public ExcelFileWriter(List<String> headerLines, List<String> footerLines) {
    this.workbookFactory = new WorkbookFactory();
    this.titleGenerator = new TitleGenerator(headerLines);
    this.resultGenerator = new ResultGenerator(footerLines);
  }

  @Override
  public void write(File targetFile, TvaMeasurementDto dto, List<String[]> rowData) {
    String excelPath = FilePathResolver.toExcelPath(targetFile);

    Workbook workbook = workbookFactory.create(dto);
    Sheet sheet = workbook.getSheetAt(0);
    Map<String, CellStyle> styles = workbookFactory.getCellStyles();

    titleGenerator.create(sheet);
    resultGenerator.create(sheet, rowData, styles, dto);

    // Write to file
    try (FileOutputStream out = new FileOutputStream(excelPath)) {
      workbook.write(out);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        workbook.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
