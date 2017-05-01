package sqlcmd.integrationTest;

import org.junit.Before;
import org.junit.Test;
import sqlcmd.ConsoleMock;
import ua.com.juja.sergiishcherbakov.sqlcmd.controller.StartController;

import static org.junit.Assert.assertEquals;

/**
 * Created by StrannikFujitsu on 27.04.2017.
 */

public class LogingUserIntegrationTest {

   private ConsoleMock consoleMock;

    String wrongBaseName;
    String baseName;
    String userName;
    String wrongUserName;
    String password;
    String wrongPassword;
// //SQLCmd|postgres|z good data

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
    public void goodAutentificationStart() throws Exception {
        // given
        consoleMock = new ConsoleMock();
        consoleMock.addIn( baseName+ "|" + userName + "|" + password);

        // when
        StartController.main(new String[0]);

        // then
        assertOut("You started program SQLCmd from Sergii Shcserbakow\n" +
                "the program can to connect to your localhost database\n" +
                "please enter your data in format:\"databaseName|userName|password\": \n" +
                "SQLCmd|postgres|z\n" +
                "connect to database\n" +
                "Connection successful!\n" +
                "Main menu:\n" +
                "Good by. See you soon.\n"
        );
    }

    @Test
    public void setWrongBaseName() throws Exception {
        // given
        consoleMock = new ConsoleMock();
        consoleMock.addIn( wrongBaseName+ "|" + userName + "|" + password);
        consoleMock.addIn( baseName+ "|" + userName + "|" + password);

        // when
        StartController.main(new String[0]);

        // then
        assertOut("You started program SQLCmd from Sergii Shcserbakow\n" +
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
                "Good by. See you soon.\n"
        );
    }

    @Test
    public void setWrongUserNameParametersName() throws Exception {
        // given
        consoleMock = new ConsoleMock();
        consoleMock.addIn( baseName+ "|" + wrongUserName + "|" + password);
        consoleMock.addIn( baseName+ "|" + userName + "|" + password);

        // when
        StartController.main(new String[0]);

        // then
        assertOut("You started program SQLCmd from Sergii Shcserbakow\n" +
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
                "Good by. See you soon.\n"
        );
    }

    @Test
    public void setWrongPasswordParametersName() throws Exception {
        // given
        consoleMock = new ConsoleMock();
        consoleMock.addIn( baseName+ "|" + userName + "|" + wrongPassword);
        consoleMock.addIn( baseName+ "|" + userName + "|" + password);

        // when
        StartController.main(new String[0]);

        // then
        assertOut("You started program SQLCmd from Sergii Shcserbakow\n" +
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
                "Good by. See you soon.\n"
        );
    }

    @Test
    public void setLessParametersName() throws Exception {
        // given
        consoleMock = new ConsoleMock();
        consoleMock.addIn( wrongBaseName+ "|"  + password);
        consoleMock.addIn( baseName+ "|" + userName + "|" + password);

        // when
        StartController.main(new String[0]);

        // then
        assertOut("You started program SQLCmd from Sergii Shcserbakow\n" +
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
                "Good by. See you soon.\n"
        );
    }

    private void assertOut(String expected, String... parameters) {
        String string = expected.replaceAll("\\n", System.lineSeparator());
        assertEquals(string, consoleMock.getOut());
    }
}
