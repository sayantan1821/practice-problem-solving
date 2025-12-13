@echo off
REM Compile Java project without Maven
REM Uses javac directly

echo Compiling Java project...

REM Create output directory
if not exist "target\classes" mkdir target\classes

REM Compile source files
javac -d target\classes -sourcepath src\main\java src\main\java\com\practice\**\*.java

if errorlevel 1 (
    echo Compilation failed!
    exit /b 1
)

echo.
echo ✅ Compilation successful!
echo Classes are in: target\classes

