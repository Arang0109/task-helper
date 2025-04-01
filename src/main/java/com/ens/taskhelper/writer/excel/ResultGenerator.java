package com.ens.taskhelper.writer.excel;

import com.ens.taskhelper.dto.TvaMeasurementDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ResultGenerator {
  private final List<String> footerLines;

  public ResultGenerator(List<String> footerLines) {
    this.footerLines = footerLines;
  }

  public void create(Sheet sheet,
                     List<String[]> rowData,
                     Map<String, CellStyle> styles,
                     TvaMeasurementDto dto) {
    double sum = 0;

    for (int i = 0; i < rowData.size(); i++) {
      Row row = sheet.createRow(i + 6);
      String[] cols = rowData.get(i);

      Cell dateCell = row.createCell(0);
      dateCell.setCellValue(dto.getDate());
      dateCell.setCellStyle(styles.get("dateStyle"));

      DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH);
      row.createCell(1).setCellValue(cols[2]);

      Cell cell = row.createCell(2);

      cell.setCellStyle(styles.get("decimalStyle"));
      cell.setCellValue(0.0);

      row.createCell(3).setCellValue("PPM OK");

      double val = Double.parseDouble(cols[4]);
      sum += val;
      row.createCell(4).setCellValue(val);

      row.createCell(5).setCellValue("PPM OK");
    }

    sheet.createRow(rowData.size() + 7).createCell(0).setCellValue(footerLines.get(0));

    Row lastRow = sheet.createRow(rowData.size() + 8);
    lastRow.createCell(0).setCellValue(footerLines.get(1));

    double avg = Math.round((sum / rowData.size()) * 10.0) / 10.0;
    Cell avgCell = lastRow.createCell(4);
    avgCell.setCellValue(avg);
    avgCell.setCellStyle(styles.get("avgStyle"));

    for(int i = 0; i < 6; i++) sheet.autoSizeColumn(i);
  }

}
