@echo off
REM Export solution to standalone format
REM Usage: export_solution.bat <solution-file-path>

if "%1"=="" (
    echo Usage: export_solution.bat ^<solution-file-path^>
    echo.
    echo Example:
    echo   export_solution.bat src\main\java\com\practice\leetcode\SortByReflection.java
    exit /b 1
)

echo Compiling project...
call compile.bat
if errorlevel 1 (
    echo Compilation failed!
    exit /b 1
)

echo Exporting solution...
java -cp target/classes com.practice.ExportSolution %1

if errorlevel 1 (
    echo Export failed!
    exit /b 1
)

echo.
echo Done!

