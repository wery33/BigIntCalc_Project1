@echo off
REM You must have a JDK installed to have access to javac and java commands

set "JAVAC=javac"
set "JAVA=java"
set "JUNIT_LIB=lib\junit-4.13.2.jar"
set "HAMCREST_LIB=lib\hamcrest-core-1.3.jar"
set "SRC=."
set "CLASSPATH=%SRC%;%JUNIT_LIB%;%HAMCREST_LIB%"

REM Classes to compile
set "CLASSES=%SRC%\Main.java %SRC%\BigIntCalculation.java %SRC%\BigIntCalculationTest.java"

REM Check the argument passed and run the corresponding target
if "%1"=="compile" goto compile
if "%1"=="test-run" goto test-run
if "%1"=="test" goto test
if "%1"=="clean" goto clean

REM No target specified, show usage
echo Usage: make.bat [compile | test-run | test | clean]
exit /b

:compile
%JAVAC% -cp %CLASSPATH% %CLASSES%
if %errorlevel% neq 0 (
    echo Compilation failed!
    exit /b %errorlevel%
)
echo Main class compiled! Run with "java Main"
exit /b

:test-run
call :compile
if %errorlevel% neq 0 exit /b %errorlevel%
%JAVA% -cp %CLASSPATH% Main < input.txt
exit /b

:test
call :compile
if %errorlevel% neq 0 exit /b %errorlevel%
%JAVA% -cp %CLASSPATH% org.junit.runner.JUnitCore BigIntCalculationTest
exit /b

:clean
del /f %SRC%\*.class
echo Project cleaned!
exit /b
