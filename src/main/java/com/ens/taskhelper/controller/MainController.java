package com.ens.taskhelper.controller;

import com.ens.taskhelper.viewmodel.TvaMeasurementViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class MainController {
  private final TvaMeasurementViewModel tvaMeasurementViewModel = new TvaMeasurementViewModel();

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

  @FXML
  public void initialize() {
    dateId.valueProperty().bindBidirectional(tvaMeasurementViewModel.dateProperty());
    companyId.textProperty().bindBidirectional(tvaMeasurementViewModel.companyProperty());
    stackId.textProperty().bindBidirectional(tvaMeasurementViewModel.stackProperty());
    timeId.textProperty().bindBidirectional(tvaMeasurementViewModel.timeProperty());
    referenceValueId.textProperty().bindBidirectional(tvaMeasurementViewModel.standardValueProperty());
    variableValueId.textProperty().bindBidirectional(tvaMeasurementViewModel.changeAmountProperty());

    consoleId.textProperty().bindBidirectional(tvaMeasurementViewModel.consoleMessageProperty());

    createDataBtn.setOnAction(e -> tvaMeasurementViewModel.onCreateData());
    createFileBtn.setOnAction(e -> tvaMeasurementViewModel.onCreateFile());
    closeBtn.setOnAction(e -> tvaMeasurementViewModel.onCloseBtn());
  }
}