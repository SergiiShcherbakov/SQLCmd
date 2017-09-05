package ua.com.juja.sergiishcherbakov.sqlcmd.integrationTest;

import org.junit.*;
import ua.com.juja.sergiishcherbakov.sqlcmd.ConsoleMock;
import ua.com.juja.sergiishcherbakov.sqlcmd.DBSetupForTests;
import ua.com.juja.sergiishcherbakov.sqlcmd.Main;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sergii Shcherbakov on 27.04.2017.
 */
@Ignore
public class LoginUserIntegrationTest {

    private ConsoleMock consoleMock;

    private String wrongBaseName;
    private String baseName;
    private String userName;
    private String wrongUserName;
    private String password;
    private String wrongPassword;

    @BeforeClass
    public static void setDatabase() throws SQLException, ClassNotFoundException {
        DBSetupForTests.createTestDatabase();
    }

    @AfterClass
    public static void cleanDatabase() throws SQLException, ClassNotFoundException {
        DBSetupForTests.deleteTestDatabase();
    }

    @Before
    public void set(){
        baseName = DBSetupForTests.getDBName();
        userName = DBSetupForTests.getDBLogin();
        password = DBSetupForTests.getDBPassword();

        wrongBaseName = "SQLCmd1";
        wrongUserName = "postgres1";
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
                "the program can connect to your local database\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                baseName + "|" + userName + "|" + password + "\n" +
               "connection to database " + baseName + " is successful\n" +
                "Main menu:\n" +
                "Enter your command or type help to get list commands:\n" +
                "exit\n"+
                "good by, see you soon.\n"
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
                "the program can connect to your local database\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                wrongBaseName + "|" + userName + "|" + password + "\n" +
                "FATAL: database \"" + wrongBaseName + "\" does not exist\n" +
                "please, try again\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                baseName + "|" + userName + "|" + password + "\n" +
               "connection to database " + baseName + " is successful\n" +
                "Main menu:\n" +
                "Enter your command or type help to get list commands:\n" +
                "exit\n"+
                "good by, see you soon.\n"
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
                "the program can connect to your local database\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                baseName + "|" + wrongUserName + "|" + password + "\n" +
                "FATAL: password authentication failed for user \"" + wrongUserName + "\"\n" +
                "please, try again\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                baseName + "|" + userName + "|" + password + "\n" +
                "connection to database " + baseName + " is successful\n" +
                "Main menu:\n" +
                "Enter your command or type help to get list commands:\n" +
                "exit\n"+
                "good by, see you soon.\n"
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
                "the program can connect to your local database\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                baseName + "|" + userName + "|" + wrongPassword + "\n" +
                "FATAL: password authentication failed for user \"" + userName + "\"\n" +
                "please, try again\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                baseName + "|" + userName + "|" + password + "\n" +
                "connection to database " + baseName +" is successful\n" +
                "Main menu:\n" +
                "Enter your command or type help to get list commands:\n" +
                "exit\n"+
                "good by, see you soon.\n"
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
                "the program can connect to your local database\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                wrongBaseName+ "|"  + password + "\n" +
                "3 parameters are expected but 2 is entered\n" +
                "please, try again\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                baseName + "|" + userName + "|" + password + "\n" +
                "connection to database " + baseName +" is successful\n" +
                "Main menu:\n" +
                "Enter your command or type help to get list commands:\n" +
                "exit\n"+
                "good by, see you soon.\n"
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
                "the program can connect to your local database\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                baseName + "|" + userName + "|" + password + "\n" +
                "connection to database " + baseName +" is successful\n" +
                "Main menu:\n" +
                "Enter your command or type help to get list commands:\n" +
                "help\n" +
                "the program supports next command:\n" +
                "\t\n" +
                "clear\t\tclear table specified by user\n" +
                "\t\t\tformat the command:\n" +
                "\t\t\tclear|tableName\n" +
                "connect\t\tconnect to database specified by user\n" +
                "\t\t\tformat the command:\n" +
                "\t\t\tconnect|database|user|password\n" +
                "create\t\tcreate new table specified by user\n" +
                "\t\t\tformat the command:\n" +
                "\t\t\tcreate|tableName|column1|column2|...|columnN\n" +
                "delete\t\tdelete row from table by name and value specified by user \n" +
                "\t\t\tformat the command:\n" +
                "\t\t\tdelete|tableName|column|value\n" +
                "drop\t\tremove tables specified by user\n" +
                "\t\t\tformat the command:\n" +
                "\t\t\tdrop|tableName\n" +
                "exit\t\texit from the program\n" +
                "find\t\tfind and print tables specified by user\n" +
                "\t\t\tformat the command:\n" +
                "\t\t\tfind|tableName\n"+
                "help\t\tget name and description of command that support the program\n" +
                "insert\t\tinsert row into table specified by user\n" +
                "\t\t\tformat the command:\n" +
                "\t\t\tinsert|tableName|column1|value1|column2|value2|...|columnN|ValueN\n"+
                "tables\t\tdisplays all tables in the database\n" +
                "update\t\tupdate table where column 1 = value1 \n" +
                "\t\t\twith values 2 ... value n in column 2 - column n specified by user\n" +
                "\t\t\tformat the command:\n" +
                "\t\t\tupdate|tableName|column1|value1|column2|value2|...|columnN|valueN\n" +
                "Enter your command or type help to get list commands:\n" +
                "exit\n" +
                "good by, see you soon.\n"
        );
    }

    private void assertOut(String expected) {
        String string = expected.replaceAll("\\n", System.lineSeparator());
        assertEquals(string, consoleMock.getOut());
    }
}
