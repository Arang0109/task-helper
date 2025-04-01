@echo off
setlocal

set "APP_HOME=%~dp0"
set "LIB_PATH=%APP_HOME%lib"
set "JAR_FILE=%APP_HOME%\snapshot\task-helper-1.0-SNAPSHOT.jar"


java ^
  --module-path "%LIB_PATH%" ^
  --add-modules javafx.controls,javafx.fxml ^
  -cp "%JAR_FILE%;%LIB_PATH%\*" ^
  com.ens.taskhelper.MainApplication

endlocal
pause