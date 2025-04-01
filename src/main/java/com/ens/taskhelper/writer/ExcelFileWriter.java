package com.ens.taskhelper.writer;

import com.ens.taskhelper.dto.TvaMeasurementDto;
import com.ens.taskhelper.util.FilePathResolver;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class ExcelFileWriter implements FileWriterStrategy {

  private final List<String> headerLines;
  private final List<String> footerLines;

  public ExcelFileWriter(List<String> headerLines, List<String> footerLines) {
    this.headerLines = headerLines;
    this.footerLines = footerLines;
  }

  @Override
  public void write(File targetFile, TvaMeasurementDto dto, List<String[]> rowData) {
    String excelPath = FilePathResolver.toExcelPath(targetFile);

    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet(dto.getDate() + " " + dto.getCompany());

    // Header Rows
    sheet.createRow(0).createCell(0).setCellValue(headerLines.get(0));
    sheet.createRow(1).createCell(0).setCellValue(headerLines.get(1));
    sheet.createRow(3).createCell(0).setCellValue(headerLines.get(2).trim());

    // Title Row
    String[] titleVal = {"DATE", "TIME", "FID B", "ACKGROUND", "FID CO", "NCENTRATION"};
    String[] lineVal = {"---------", "--------", "------", "--------------", "-------", "-------------"};

    Row titleRow = sheet.createRow(4);
    for (int i = 0; i < titleVal.length; i++) {
      titleRow.createCell(i).setCellValue(titleVal[i]);
    }

    Row lineRow = sheet.createRow(5);
    for (int i = 0; i < lineVal.length; i++) {
      lineRow.createCell(i).setCellValue(lineVal[i]);
    }

    // Data Rows
    CreationHelper createHelper = workbook.getCreationHelper();
    CellStyle dateStyle = workbook.createCellStyle();
    dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MMM-yy"));

    double sum = 0;

    for (int i = 0; i < rowData.size(); i++) {
      Row row = sheet.createRow(i + 6);
      String[] cols = rowData.get(i);

      // DATE
      Cell dateCell = row.createCell(0);
      dateCell.setCellValue(dto.getDate());
      dateCell.setCellStyle(dateStyle);

      // TIME
      DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH);
      row.createCell(1).setCellValue(cols[2]);

      // FID B
      Cell cell = row.createCell(2);

      CellStyle style = workbook.createCellStyle();
      DataFormat format = workbook.createDataFormat();
      style.setDataFormat(format.getFormat("0.0")); // 또는 "0.0##" 등

      cell.setCellStyle(style);
      cell.setCellValue(0.0);

      // Background Text
      row.createCell(3).setCellValue("PPM OK");

      // FID CO
      double val = Double.parseDouble(cols[4]);
      sum += val;
      row.createCell(4).setCellValue(val);

      // Concentration Text
      row.createCell(5).setCellValue("PPM OK");
    }

    // Footer Rows
    sheet.createRow(rowData.size() + 7).createCell(0).setCellValue(footerLines.get(0));

    Row lastRow = sheet.createRow(rowData.size() + 8);
    lastRow.createCell(0).setCellValue(footerLines.get(1));

    // 평균값
    double avg = Math.round((sum / rowData.size()) * 10.0) / 10.0;
    Cell avgCell = lastRow.createCell(4);
    avgCell.setCellValue(avg);

    CellStyle yellowStyle = workbook.createCellStyle();
    yellowStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
    yellowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    avgCell.setCellStyle(yellowStyle);

    for(int i = 0; i < 6; i++) sheet.autoSizeColumn(i);

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
