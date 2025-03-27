package com.ens.taskhelper.viewmodel;

import com.ens.taskhelper.dto.TvaMeasurementDto;
import com.ens.taskhelper.service.FileGeneratorService;
import com.ens.taskhelper.util.DateFormatter;
import com.ens.taskhelper.util.Validator;
import javafx.beans.property.*;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.LocalDate;

public class TvaMeasurementViewModel {
  private boolean dataReady = false;

  private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
  private final StringProperty company = new SimpleStringProperty();
  private final StringProperty stack = new SimpleStringProperty();
  private final StringProperty time = new SimpleStringProperty();
  private final StringProperty standardValue = new SimpleStringProperty();
  private final StringProperty changeAmount = new SimpleStringProperty();
  private final StringProperty consoleMessage = new SimpleStringProperty();
  private final ObjectProperty<Paint> consoleColor = new SimpleObjectProperty<>(Paint.valueOf("black"));

  public ObjectProperty<LocalDate> dateProperty() { return date; }
  public StringProperty companyProperty() { return company; }
  public StringProperty stackProperty() { return stack; }
  public StringProperty timeProperty() { return time; }
  public StringProperty standardValueProperty() { return standardValue; }
  public StringProperty changeAmountProperty() { return changeAmount; }
  public StringProperty consoleMessageProperty() { return consoleMessage; }
  public ObjectProperty<Paint> consoleColorProperty() { return consoleColor; }

  public void onCreateData() {
    Validator validator = new Validator();
    String result = validator.check(
        dateProperty().get(),
        companyProperty().getValue(),
        stackProperty().getValue(),
        timeProperty().getValue(),
        standardValueProperty().getValue(),
        changeAmountProperty().getValue()
    );

    if (result.equals("OK")) {
      consoleMessageProperty().setValue("✅ 유효성 검사 통과");
      consoleColorProperty().set(Paint.valueOf("blue"));
      dataReady = true;
    } else {
      consoleMessageProperty().setValue("❌ " + result);
      consoleColorProperty().set(Paint.valueOf("red"));
    }
  }

  public void onCreateFile() {
    if(!dataReady) {
      consoleMessageProperty().setValue("먼저 데이터 생성을 해주세요.");
      consoleColorProperty().set(Paint.valueOf("red"));
      return;
    }

    DateFormatter formatter = new DateFormatter();
    TvaMeasurementDto dataDto = new TvaMeasurementDto(
        dateProperty().get(),
        companyProperty().getValue(),
        stackProperty().getValue(),
        formatter.timeParse(timeProperty().getValue()),
        Double.parseDouble(standardValueProperty().getValue()),
        Double.parseDouble(changeAmountProperty().getValue())
    );

    FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showSaveDialog(null);
    if (file != null) {
      FileGeneratorService fileGeneratorService = new FileGeneratorService(file);
      fileGeneratorService.createFiles(dataDto);
    }

    consoleMessageProperty().setValue("파일 생성 완료");
    consoleColorProperty().set(Paint.valueOf("blue"));
  }

  public void onCloseBtn() { System.exit(0); }
}
