package ua.com.juja.sergiishcherbakov.sqlcmd.integrationTest;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sergiishcherbakov.sqlcmd.ConsoleMock;
import ua.com.juja.sergiishcherbakov.sqlcmd.Main;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sergii Shcherbakov on 27.04.2017.
 * SQLCmd|postgres|z good data for testing in local postgreSQL database
 */


public class LoginUserIntegrationTest {

    private ConsoleMock consoleMock;

    private String wrongBaseName;
    private String baseName;
    private String userName;
    private String wrongUserName;
    private String password;
    private String wrongPassword;

    @Before
    public void set(){

        wrongBaseName = "SQLCmd1";
        baseName = "SQLCmd";
        userName = "postgres";
        wrongUserName = "postgres1";
        password = "z";
        wrongPassword = "zz";
    }

    @Test
    public void goodAuthorizationStart() throws Exception {
        // given
        consoleMock = new ConsoleMock();
        consoleMock.addIn( baseName+ "|" + userName + "|" + password);
        consoleMock.addIn( "exit");

        // when
        Main.main(new String[]{});

        // then
        assertOut("You started program SQLCmd from Sergii Shcherbakov\n" +
                "the program can to connect to your localhost database\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                "SQLCmd|postgres|z\n" +
                "connect to database\n" +
                "Connection successful!\n" +
                "Main menu:\n" +
                "Enter your command or type help to get help:\n" +
                "exit\n"+
                "Good by. See you soon.\n"
        );
    }

    @Test
    public void setWrongBaseName() throws Exception {
        // given
        consoleMock = new ConsoleMock();
        consoleMock.addIn( wrongBaseName+ "|" + userName + "|" + password);
        consoleMock.addIn( baseName+ "|" + userName + "|" + password);
        consoleMock.addIn( "exit");

        // when
        Main.main(new String[]{});
        // then
        assertOut("You started program SQLCmd from Sergii Shcherbakov\n" +
                "the program can to connect to your localhost database\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                "SQLCmd1|postgres|z\n" +
                "FATAL: database \"SQLCmd1\" does not exist\n" +
                "please, try again\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                "SQLCmd|postgres|z\n" +
                "connect to database\n" +
                "Connection successful!\n" +
                "Main menu:\n" +
                "Enter your command or type help to get help:\n" +
                "exit\n"+
                "Good by. See you soon.\n"
        );
    }

    @Test
    public void setWrongUserNameParametersName() throws Exception {
        // given
        consoleMock = new ConsoleMock();
        consoleMock.addIn( baseName+ "|" + wrongUserName + "|" + password);
        consoleMock.addIn( baseName+ "|" + userName + "|" + password);
        consoleMock.addIn( "exit");

        // when
        Main.main(new String[]{});

        // then
        assertOut("You started program SQLCmd from Sergii Shcherbakov\n" +
                "the program can to connect to your localhost database\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                "SQLCmd|postgres1|z\n" +
                "FATAL: password authentication failed for user \"postgres1\"\n" +
                "please, try again\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                "SQLCmd|postgres|z\n" +
                "connect to database\n" +
                "Connection successful!\n" +
                "Main menu:\n" +
                "Enter your command or type help to get help:\n" +
                "exit\n"+
                "Good by. See you soon.\n"
        );
    }

    @Test
    public void setWrongPasswordParametersName() throws Exception {
        // given
        consoleMock = new ConsoleMock();
        consoleMock.addIn( baseName+ "|" + userName + "|" + wrongPassword);
        consoleMock.addIn( baseName+ "|" + userName + "|" + password);
        consoleMock.addIn( "exit");

        // when
        Main.main(new String[]{});

        // then
        assertOut("You started program SQLCmd from Sergii Shcherbakov\n" +
                "the program can to connect to your localhost database\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                "SQLCmd|postgres|zz\n" +
                "FATAL: password authentication failed for user \"postgres\"\n" +
                "please, try again\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                "SQLCmd|postgres|z\n" +
                "connect to database\n" +
                "Connection successful!\n" +
                "Main menu:\n" +
                "Enter your command or type help to get help:\n" +
                "exit\n"+
                "Good by. See you soon.\n"
        );
    }

    @Test
    public void setLessParametersName() throws Exception {
        // given
        consoleMock = new ConsoleMock();
        consoleMock.addIn( wrongBaseName+ "|"  + password);
        consoleMock.addIn( baseName+ "|" + userName + "|" + password);
        consoleMock.addIn( "exit");

        // when
        Main.main(new String[]{});

        // then
        assertOut("You started program SQLCmd from Sergii Shcherbakov\n" +
                "the program can to connect to your localhost database\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                "SQLCmd1|z\n" +
                "3 parameters are expected but 2 is entered please, try again\n" +
                "please, try again\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                "SQLCmd|postgres|z\n" +
                "connect to database\n" +
                "Connection successful!\n" +
                "Main menu:\n" +
                "Enter your command or type help to get help:\n" +
                "exit\n"+
                "Good by. See you soon.\n"
        );
    }

    @Test
    public void connectToBaseHelpAndExit() throws Exception {
        // given
        consoleMock = new ConsoleMock();
        consoleMock.addIn( baseName+ "|" + userName + "|" + password);
        consoleMock.addIn( "help");
        consoleMock.addIn( "exit");

        // when
        Main.main(new String[]{});
        // then
        assertOut("You started program SQLCmd from Sergii Shcherbakov\n" +
                "the program can to connect to your localhost database\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                "SQLCmd|postgres|z\n" +
                "connect to database\n" +
                "Connection successful!\n" +
                "Main menu:\n" +
                "Enter your command or type help to get help:\n" +
                "help\n" +
                "The program support next command:\n" +
                "clear\t\tclear table specified by user\n" +
                "\t\tformat the command:\n" +
                "\t\t clear|\"table name\"\n" +
                "connect\t\tconnect to database specified by user\n" +
                "\t\tformat the command:\n" +
                "\t\t connect|database|user|password\n" +
                "create\t\tcreate new table specified by user\n" +
                "\t\tformat the command:\n" +
                "\t\tcreate|tableName|column1|column2|...|columnN\n" +
                "delete\t\tdelete row from table by name and value specified by user \n" +
                "\t\tformat the command:\n" +
                "\t\t delete|tableName|column|value\n" +
                "drop\t\tremove tables specified by user\n" +
                "\t\tformat the command:\n" +
                "\t\t drop|\"table name\"\n" +
                "exit\t\texit from the program\n" +
                "find\t\tfind and print tables specified by user\n" +
                "\t\tformat the command:\n" +
                "\t\t find|\"table name\"\n"+
                "help\t\tget name and description of command that support the program\n" +
                "insert\t\tinsert row into table specified by user\n" +
                "\t\tformat the command:\n" +
                "\t\t insert|\"table name\"|\"column1\"|\"value1\"|\"column2\"|\"value2\"|...|\"columnN\"|\"ValueN\"\n"+
                "tables\t\tdisplays all tables in the database\n" +
                "update\t\tupdate table where column 1 = value1 \n" +
                "\t\twith values 2 ... value n in column 2 - column n specified by user\n" +
                "\t\tformat the command:\n" +
                "\t\tupdate|tableName|column1|value1|column2|value2|...|columnN|valueN\n" +
                "Enter your command or type help to get help:\n" +
                "exit\n" +
                "Good by. See you soon.\n"
        );
    }

    private void assertOut(String expected) {
        String string = expected.replaceAll("\\n", System.lineSeparator());
        assertEquals(string, consoleMock.getOut());
    }
}
