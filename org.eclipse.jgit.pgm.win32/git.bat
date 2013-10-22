@echo off
if defined JAVA_HOME (set JAVA="%JAVA_HOME%\bin\java") ELSE (set JAVA=java)
%JAVA% -cp "%JGIT_CLASSPATH%" org.eclipse.jgit.pgm.Main %*
