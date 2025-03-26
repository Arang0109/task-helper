package com.ens.taskhelper.controller;

import com.ens.taskhelper.service.FileService;
import com.ens.taskhelper.dto.CreateDataDto;
import com.ens.taskhelper.util.DateFormatter;
import com.ens.taskhelper.util.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.LocalDate;

public class MainController {
  @FXML public DatePicker dateId;
  @FXML public TextField companyId;
  @FXML public TextField stackId;
  @FXML public TextField timeId;
  @FXML public TextField referenceValueId;
  @FXML public TextField variableValueId;

  @FXML public Text consoleId;

  @FXML public Button createDataBtn;
  @FXML public Button createFileBtn;
  @FXML public Button closeBtn;

  private LocalDate date;
  private String company;
  private String stack;
  private String timeStr;
  private String refStr;
  private String varStr;
  private boolean dataReady = false;

  @FXML
  protected void onCreateData() {
    this.date = dateId.getValue();
    this.company = companyId.getText();
    this.stack = stackId.getText();
    this.timeStr = timeId.getText();
    this.refStr = referenceValueId.getText();
    this.varStr = variableValueId.getText();

    Validator validator = new Validator();
    String result = validator.check(date, company, stack, timeStr, refStr, varStr);

    if (result.equals("OK")) {
      consoleId.setText("✅ 유효성 검사 통과");
      consoleId.setFill(Paint.valueOf("blue"));
      dataReady = true;
    } else {
      consoleId.setText("❌ " + result);
      consoleId.setFill(Paint.valueOf("red"));
    }
  }

  @FXML
  protected void onCreateFile() {
    if(!dataReady) {
      consoleId.setText("먼저 데이터 생성을 해주세요.");
      consoleId.setFill(Paint.valueOf("red"));
      return;
    }

    DateFormatter formatter = new DateFormatter();
    CreateDataDto dataDto = new CreateDataDto(
        date,
        company,
        stack,
        formatter.timeParse(timeStr),
        Double.parseDouble(refStr),
        Double.parseDouble(varStr)
    );

    FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showSaveDialog(null);
    if (file != null) {
      FileService fileService = new FileService(dataDto, file);
      fileService.saveToText();
    }

    consoleId.setText("파일 생성 완료");
    consoleId.setFill(Paint.valueOf("blue"));
  }


  @FXML
  protected void onCloseBtn() {
    System.exit(0);
  }
}