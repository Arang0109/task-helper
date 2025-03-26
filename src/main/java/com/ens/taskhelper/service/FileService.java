package com.ens.taskhelper.service;

import com.ens.taskhelper.dto.CreateDataDto;
import com.ens.taskhelper.util.DateFormatter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class FileService {
  private final List<String> headerString = new ArrayList<>();
  private final String title;
  private final CreateDataDto dataDto;
  private final File targetFile;

  public FileService(CreateDataDto dataDto, File targetFile) {
    headerString.add("LOGGED DATA");
    headerString.add("VER= 2.00");
    headerString.add("AUTO DATA                  ");
    headerString.add("  DATE       TIME     FID BACKGROUND        FID CONCENTRATION  ");
    headerString.add("---------  --------  --------------------  --------------------");

    this.dataDto = dataDto;
    this.targetFile = targetFile;
    this.title = dataDto.getDate() + " " + dataDto.getCompany() + " " + dataDto.getStack();
  }

  public void saveToText() {
    DateFormatter dateFormatter = new DateFormatter();

//    String fileName = title + ".txt";

    String date = dateFormatter.dateFormat(dataDto.getDate());
    LocalTime time = dataDto.getTime();
    double referenceValue = dataDto.getReferenceValue();

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile))) {
      // header design
      writer.write(headerString.getFirst());
      writer.newLine();
      writer.write(headerString.get(1));
      writer.newLine();
      writer.newLine();
      writer.write(headerString.get(2));
      writer.newLine();
      writer.write(headerString.get(3));
      writer.newLine();
      writer.write(headerString.getLast());
      writer.newLine();

      // body design
      List<String[]> rowData = new ArrayList<>();

      Random random = new Random();
      int ranNumber = random.nextInt(30) + 120;

      for (int i = 0; i < ranNumber; i++) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(timeFormatter);
        double randDouble = random.nextDouble(dataDto.getVariableValue()) - dataDto.getVariableValue() / 2.0;
        randDouble += referenceValue;

        String rValue;
        if (randDouble >= 100) {
          rValue = String.format("%.0f", randDouble); // 정수로 출력
        } else {
          rValue = String.format("%.1f", randDouble); // 소수 첫째 자리
        }

        rowData.add(new String[]{date, formattedTime, "0", "PPM OK", rValue, "PPM OK"});

        writer.write(date + "  " + formattedTime + "     0.0 PPM OK            " + rValue + " PPM OK         ");
        writer.newLine();

        time = time.plusSeconds(15);
      }

      saveToExcel(rowData);

    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("❌ 파일 생성 실패: " + e.getMessage());
    }
  }

  private void saveToExcel(List<String[]> rowData) {
    String basePath = targetFile.getAbsolutePath();
    String excelFilePath;

    // 확장자 제거하고 .xlsx로 붙이기
    if (basePath.endsWith(".txt")) {
      excelFilePath = basePath.replace(".txt", ".xlsx");
    } else {
      excelFilePath = basePath + ".xlsx"; // 예외 처리
    }

    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet(title);

    sheet.createRow(0).createCell(0).setCellValue(headerString.getFirst());
    sheet.createRow(1).createCell(0).setCellValue(headerString.get(1));
    sheet.createRow(3).createCell(0).setCellValue(headerString.get(2));

    String[] titleVal = {"DATE", "TIME", "FID B", "ACKGROUnd", "FID CO", "NCENTRATION"};
    String[] lineVal = {"---------", "--------", "------", "--------------", "-------", "-------------"};

    Row titleRow = sheet.createRow(4);
    for (int i = 0; i < titleVal.length; i++) {
      titleRow.createCell(i).setCellValue(titleVal[i]);
    }

    Row lineRow = sheet.createRow(5);
    for (int i = 0; i < lineVal.length; i++) {
      lineRow.createCell(i).setCellValue(lineVal[i]);
    }

    double sum = 0;

    for (int i = 0; i < 120; i++) {
      Row row = sheet.createRow(i + 6);
      String[] cols = rowData.get(i);

      CreationHelper createHelper = workbook.getCreationHelper();
      CellStyle dateStyle = workbook.createCellStyle();
      dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MMM-yy"));

      Cell dateCell = row.createCell(0);

      dateCell.setCellValue(dataDto.getDate());
      dateCell.setCellStyle(dateStyle);

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss", Locale.ENGLISH);
      String timeText = dataDto.getTime().format(formatter);
      row.createCell(1).setCellValue(timeText);

      // 숫자들 (double로 저장)
      sum += Double.parseDouble(cols[4]);
      row.createCell(2).setCellValue(Integer.parseInt(cols[2])); // FID 배경
      row.createCell(3).setCellValue(cols[3]);
      row.createCell(4).setCellValue(Double.parseDouble(cols[4]));
      row.createCell(5).setCellValue(cols[5]);
    }

    double average = Math.round((sum / 120.0) * 10.0) / 10.0;
    sheet.createRow(127).createCell(0).setCellValue("END");
    Row row = sheet.createRow(128);
    row.createCell(0).setCellValue("→");
    Cell cell = row.createCell(4);
    cell.setCellValue(average);

    CellStyle style = workbook.createCellStyle();
    style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

// 스타일 적용
    cell.setCellStyle(style);

    try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
      workbook.write(fileOut);
      workbook.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
