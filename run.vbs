Set WshShell = CreateObject("WScript.Shell")
WshShell.Run "javaw --module-path lib --add-modules javafx.controls,javafx.fxml -cp task-helper-1.0-SNAPSHOT.jar;lib/* com.ens.taskhelper.MainApplication", 0
