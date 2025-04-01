@echo off
setlocal

:: JDK 경로 (필요 시 수정)
set "JAVA_HOME=C:\jdk21"
set "PATH=%JAVA_HOME%\bin;%PATH%"

:: 앱 설정
set "APP_NAME=TaskHelper"
set "MAIN_CLASS=com.ens.taskhelper.MainApplication"
set "JAR_NAME=task-helper-1.0-SNAPSHOT.jar"
set "INPUT_DIR=D:\CodeStorage\javaFX\task-helper\build\libs"
set "LIB_DIR=D:\CodeStorage\javaFX\task-helper\lib"
set "OUTPUT_DIR=D:\CodeStorage\javaFX\task-helper\dist"

jpackage ^
  --type exe ^
  --name "%APP_NAME%" ^
  --input "%INPUT_DIR%" ^
  --main-jar "%JAR_NAME%" ^
  --main-class "%MAIN_CLASS%" ^
  --dest "%OUTPUT_DIR%" ^
  --java-options "--enable-preview" ^
  --java-options "-Xmx512m" ^
  --win-console ^
  --verbose

pause