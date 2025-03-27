@echo off
java ^
  --module-path D:\Desktop\TaskHelper\lib ^
  --add-modules javafx.controls,javafx.fxml ^
  -cp D:\Desktop\TaskHelper\task-helper-1.0-SNAPSHOT.jar;D:\Desktop\TaskHelper\lib\* ^
  com.ens.taskhelper.MainApplication
