ECHO OFF

REM Replace deppeler with your CS login user name
REM Be sure there are no additional blank spaces before or after your user name
set CSLOGIN=deppeler

REM May edit the path to these variables to build and run from command line
set JC=javac
set JAVA=java
set CP= -classpath "."

REM DO NOT EDIT THESE VARIABLES
set BESTLINUX=%CSLOGIN%@best-linux.cs.wisc.edu
set HANDINDIR=/p/course/cs400-deppeler/handin/202001/e2/%CSLOGIN%
set APP=Main
set ACTION=%1%

REM Display handin directory to the user
ECHO HANDINDIR = %HANDINDIR%

IF "%ACTION%"=="submit" (
  REM Copy all files in currect directory to your cs400 e2 handin directory
  ECHO ON
  scp *.* %CSLOGIN%@best-linux.cs.wisc.edu:%HANDINDIR%
) ELSE IF "%ACTION%"=="list" (
  REM List the contents of your cs400 e2 handin directory
  ECHO ON
  REM ssh %CSLOGIN%@best-linux.cs.wisc.edu ls -l %HANDINDIR%
  ssh %BESTLINUX% ls -l %HANDINDIR%
) ELSE IF "%ACTION%"=="build" (
  REM Compile your project from the command line
  ECHO ON
  javac *.java
) ELSE IF "%ACTION%"=="run" (
  REM Run your project from the command line
  ECHO ON
  java Main data.csv
) ELSE IF "%ACTION%"=="deleteall" (
  REM Delete the contents of your cs400 e2 handin directory
  ECHO ON
  REM ssh %CSLOGIN%@best-linux.cs.wisc.edu rm %HANDINDIR%/%.*
  ssh %BESTLINUX% rm %HANDINDIR%/*.*
) ELSE (
  ECHO 1. If /e2/deppeler is showing at end of your handin directory,
  ECHO    Edit handin.bat and set the CSLOGIN variable to your CS user name
  ECHO 2. Type the handin action you wish to execute
  ECHO ========== HANDIN ACTIONS ===========
  ECHO handin submit - to submit files in current directory to your handin directory
  ECHO                 All program files must be in project directory. 
  ECHO                 Do not use separate source and bin or packages.
  ECHO handin list   - to list contents of your handin directory
  ECHO handin build  - to compile *.java from the command line
  ECHO handin run    - to run Main from the command line
  ECHO handin help   - to see this message
)

