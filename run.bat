@echo off
if not exist out mkdir out
javac -d out -sourcepath src src\com\auca\main\Main.java
if errorlevel 1 (
  echo Compilation failed. Check the messages above.
  pause
  exit /b 1
)
java -cp out com.auca.main.Main
pause
