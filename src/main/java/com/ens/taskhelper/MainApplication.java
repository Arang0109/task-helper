package com.ens.taskhelper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
  @Override
  public void start(Stage stage) {
    try {
      Font.loadFont(
          Objects.requireNonNull(getClass().getResource("/fonts/NotoSansKR-Regular.ttf")).toExternalForm(),
          12
      );

      FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/com/ens/taskhelper/makerTVA.fxml"));
      Scene scene = new Scene(fxmlLoader.load());

      stage.setScene(scene);
      stage.setTitle("TaskHelper");
      stage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public static void main(String[] args) {
    launch();
  }
}