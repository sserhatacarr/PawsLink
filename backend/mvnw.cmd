@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    https://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM ----------------------------------------------------------------------------

@REM ----------------------------------------------------------------------------
@REM Apache Maven Wrapper startup batch script, version 3.3.2
@REM
@REM Optional ENV vars:
@REM -----------------
@REM   JAVA_HOME - location of a JDK home dir, required if not set in your PATH
@REM   MAVEN_BATCH_ECHO - set to 'on' to enable the echoing of the batch commands
@REM   MAVEN_BATCH_PAUSE - set to 'on' to wait for a key stroke before ending
@REM   MAVEN_OPTS - parameters passed to the Java VM when running Maven
@REM     e.g. to debug Maven itself, use
@REM       set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000
@REM   MAVEN_SKIP_RC - flag to disable loading of mavenrc files
@REM ----------------------------------------------------------------------------

@IF "%__MVNW_ARG0_NAME__%"=="" (SET "BASE_DIR=%~dp0") ELSE SET "BASE_DIR=%__MVNW_ARG0_NAME__%"

@SET MAVEN_PROJECTBASEDIR=%MAVEN_BASEDIR%
@IF NOT "%MAVEN_PROJECTBASEDIR%"=="" GOTO endDetectBaseDir

@SET EXEC_DIR=%CD%
@SET WDIR=%EXEC_DIR%
:findBaseDir
@IF EXIST "%WDIR%"\.mvn GOTO baseDirFound
@cd ..
@SET WDIR=%CD%
@IF NOT "%WDIR%"=="%EXEC_DIR%" GOTO findBaseDir
@SET MAVEN_PROJECTBASEDIR=%EXEC_DIR%
@GOTO endDetectBaseDir

:baseDirFound
@SET MAVEN_PROJECTBASEDIR=%WDIR%
@cd "%EXEC_DIR%"

:endDetectBaseDir
@IF NOT EXIST "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar" (
  @IF NOT "%MVNW_REPOURL%"=="" (
    @SET DOWNLOAD_URL="%MVNW_REPOURL%/org/apache/maven/wrapper/maven-wrapper/3.3.2/maven-wrapper-3.3.2.jar"
  ) ELSE (
    @SET DOWNLOAD_URL="https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.3.2/maven-wrapper-3.3.2.jar"
  )

  @echo Downloading from: %DOWNLOAD_URL%
  @powershell -Command "&{"^
    "$webclient = new-object System.Net.WebClient;"^
    "if (-not ([string]::IsNullOrEmpty('%MVNW_USERNAME%') -and [string]::IsNullOrEmpty('%MVNW_PASSWORD%'))) {"^
    "$webclient.Credentials = new-object System.Net.NetworkCredential('%MVNW_USERNAME%', '%MVNW_PASSWORD%');"^
    "}"^
    "[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; $webclient.DownloadFile('%DOWNLOAD_URL%', '%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar')"^
    "}"
  if "%ERRORLEVEL%"=="0" GOTO :downloadSuccess
  echo There was an error downloading maven-wrapper.jar, please try to download manually: %DOWNLOAD_URL%
  GOTO :error

  :downloadSuccess
)

@SETLOCAL EnableExtensions
@SET WRAPPER_JAR="%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar"
@SET WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

@SET DOWNLOAD_URL="https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.3.2/maven-wrapper-3.3.2.jar"

FOR /F "usebackq tokens=1,2 delims==" %%A IN ("%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.properties") DO (
  IF "%%A"=="wrapperUrl" SET DOWNLOAD_URL=%%B
)

@SET MAVEN_JAVA_EXE="%JAVA_HOME%\bin\java.exe"
@SET javaExecutable="%JAVA_HOME%\bin\java.exe"
IF NOT EXIST %MAVEN_JAVA_EXE% (
  @ECHO Error: JAVA_HOME is not defined correctly.
  @ECHO   We cannot execute %MAVEN_JAVA_EXE%
  @GOTO error
)

@SET MAVEN_OPTS_TEMP=
FOR /F "usebackq delims=" %%a IN ("%MAVEN_PROJECTBASEDIR%\.mvn\jvm.config") DO @SET MAVEN_OPTS_TEMP=!MAVEN_OPTS_TEMP! %%a
@SET MAVEN_OPTS=%MAVEN_OPTS_TEMP% %MAVEN_OPTS%

@SET MAVEN_CMD_LINE_ARGS=%MAVEN_CONFIG% %*

%MAVEN_JAVA_EXE% ^
  %MAVEN_OPTS% ^
  %MAVEN_DEBUG_OPTS% ^
  -classpath %WRAPPER_JAR% ^
  "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" ^
  %WRAPPER_LAUNCHER% %MAVEN_CONFIG% %*

IF ERRORLEVEL 1 GOTO error
GOTO end

:error
SET ERROR_CODE=1

:end
@ENDLOCAL & SET ERROR_CODE=%ERROR_CODE%

IF NOT "%MAVEN_SKIP_RC%"=="" GOTO skipRcPost
@REM check for post script, once with legacy .bat ending and once with .cmd ending
IF EXIST "%HOME%\mavenrc_post.bat" CALL "%HOME%\mavenrc_post.bat"
IF EXIST "%HOME%\mavenrc_post.cmd" CALL "%HOME%\mavenrc_post.cmd"
:skipRcPost

@REM pause the script if MAVEN_BATCH_PAUSE is set to 'on'
IF "%MAVEN_BATCH_PAUSE%"=="on" PAUSE

IF "%MAVEN_TERMINATE_CMD%"=="on" EXIT %ERROR_CODE%

CMD /C EXIT /B %ERROR_CODE%
