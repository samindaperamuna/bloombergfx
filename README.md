# Bloomberg FX

This is an application to analyze, validate and store data from a set of CSV files.

## How to set up the project.

Install mysql server and create a database named bloombergfx either with root permission or under a new user. Change the "Application.properties" to reflect the database settings. 

Build using the following command (Linux terminal and Windows powershell). Make sure you have set "JAVA_HOME" to a valid JDK location.
```bash
.\gradlew build
```
Run the project using the following command.
```bash
java -jar .\build\libs\bloombergfx-0.0.1.jar
```
