# Launchers

## SpringBoot application

Launchers are here to configure a development environment for a developers needs. E.g. the application works with a logging facade and a logger is not included in the classpath to allow the user to use his one according to his own needs. This is not practical for a developer. Therefore the configuration can be set during development time by utilizing the launchers.

By copying the file "vfs.launch.tpl" to "vfs.launch" and modifying the following line (line 14) the logger can be configured:
```xml
<stringAttribute key="spring.boot.app.properties" value="logging.config=config/logback.xml"/>
```
It can then be ran by right clicking onto the file "vfs.launch" > "Run As" > "vfs"

![Launch custom configuration](../doc/img/springconfig1.png "Launch custom configuration")

The configuration file for the logback logger resides in the folder "config".

## JUnit test

The JUnit tests also require logging and configuration is externalized. The file "GitTest.launch.tpl" can be renamed to "GitTest.launch" to execute the whole test class "GitTest".

The location of the logback configuration file is configured in line 16:

```xml
<stringAttribute key="org.eclipse.jdt.launching.VM_ARGUMENTS" value="-ea&#13;&#10;-Dlogback.configurationFile=config/logback.xml"/>
```

## Logging output

The included logging configuration outputs the log information into the directory "logs". The log files and the custom launchers are ignored by Git because they are used by developers for easy running, debugging and development of the application.