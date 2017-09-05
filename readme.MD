## Synopsis
This is my educational project. The program in console worked with database.
Now it will work like web application.
It has MVC architecture. It is used JDBC, Maven, PostgreSQL, JUnit, Mockito,
Reflections. In the program I use patterns: MVC, Command, TemplateMethod, FabricMethod, Strategy

## Tests
For tests you have to have
- installed PostgreSQL in your computer with some database,
- and configure sqlcmd.properties with your parameters:
        - REAL_DB = "some_database";
        - DB_LOGIN = "login_for_the_database";
        - DB_PASSWORD = "password_for_the_database";

database REAL_DB will not be used in tests.