@echo off
setlocal
where gradle >nul 2>nul
if errorlevel 1 (
  echo Gradle was not found. Open this project in IntelliJ IDEA and use its Gradle panel,
  echo or install Gradle 8.14+ and ensure the gradle command works in Command Prompt.
  pause
  exit /b 1
)
gradle build --no-daemon
if errorlevel 1 pause
