package com.ens.taskhelper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
  @Override
  public void start(Stage stage) {
    try {
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