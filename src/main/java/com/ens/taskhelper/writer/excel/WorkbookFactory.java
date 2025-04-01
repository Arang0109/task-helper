package com.ens.taskhelper.writer.excel;

import com.ens.taskhelper.dto.TvaMeasurementDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.HashMap;
import java.util.Map;

public class WorkbookFactory {
  Map<String, CellStyle> cellStyles;

  public WorkbookFactory() {
    this.cellStyles = new HashMap<>();
  }

  public Workbook create(TvaMeasurementDto dto) {
    Workbook workbook = new XSSFWorkbook();

    String sheetName = dto.getDate() + " " + dto.getCompany();
    workbook.createSheet(sheetName);
    createSheetStyles(workbook);

    return workbook;
  }

  private void createSheetStyles(Workbook workbook) {
    CreationHelper createHelper = workbook.getCreationHelper();

    CellStyle dateStyle = workbook.createCellStyle();
    dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MMM-yy"));

    CellStyle decimalStyle = workbook.createCellStyle();
    decimalStyle.setDataFormat(workbook.createDataFormat().getFormat("0.0"));

    CellStyle avgStyle = workbook.createCellStyle();
    avgStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
    avgStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    cellStyles.put("dateStyle", dateStyle);
    cellStyles.put("decimalStyle", decimalStyle);
    cellStyles.put("avgStyle", avgStyle);
  }

  public Map<String, CellStyle> getCellStyles() {
    return cellStyles;
  }
}
