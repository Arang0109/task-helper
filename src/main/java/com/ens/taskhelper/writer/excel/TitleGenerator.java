package com.ens.taskhelper.writer.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

public class TitleGenerator {
  private final List<String> headerLines;

  public TitleGenerator(List<String> headerLines) {
    this.headerLines = headerLines;
  }

  public void create(Sheet sheet) {
    String[] titleVal = {"DATE", "TIME", "FID B", "ACKGROUND", "FID CO", "NCENTRATION"};
    String[] lineVal = {"---------", "--------", "------", "--------------", "-------", "-------------"};

    sheet.createRow(0).createCell(0).setCellValue(headerLines.get(0));
    sheet.createRow(1).createCell(0).setCellValue(headerLines.get(1));
    sheet.createRow(3).createCell(0).setCellValue(headerLines.get(3).trim());

    Row titleRow = sheet.createRow(4);
    for (int i = 0; i < titleVal.length; i++) {
      titleRow.createCell(i).setCellValue(titleVal[i]);
    }

    Row lineRow = sheet.createRow(5);
    for (int i = 0; i < lineVal.length; i++) {
      lineRow.createCell(i).setCellValue(lineVal[i]);
    }
  }
}
