set SCRIPT_DIR=%~dp0
java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=127.0.0.1:5005 -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=256m -Xmx1024M -Xss2M -jar "%SCRIPT_DIR%\sbt-launch-0.12.1.jar" %*
