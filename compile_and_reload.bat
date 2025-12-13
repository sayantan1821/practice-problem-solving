@echo off
REM Compile and provide JShell reload instructions

echo Compiling project...
call mvn compile -q

if errorlevel 1 (
    echo Compilation failed!
    exit /b 1
)

echo.
echo ✅ Compilation successful!
echo.
echo In JShell, run:
echo   /reset
echo   import com.practice.leetcode.SortByReflection;
echo.
echo Or use: /open reload.jsh

