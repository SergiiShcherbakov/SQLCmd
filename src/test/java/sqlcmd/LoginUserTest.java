package sqlcmd;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sergiishcherbakov.sqlcmd.controller.StartController;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.JDBCPostgresSQLDatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.ConsoleViewer;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by StrannikFujitsu on 27.04.2017.
 */

public class LoginUserTest {

   private DatabaseManager  dbm;
   private Viewer viewer;
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
        dbm = new JDBCPostgresSQLDatabaseManager();
        viewer = new ConsoleViewer();


        wrongBaseName = "SQLCmd1";
        baseName = "SQLCmd";
        userName = "postgres";
        wrongUserName = "postgres1";
        password = "z";
        wrongPassword = "zz";
    }

    @Test
    public void wrongBaseNameInStart() throws Exception {
        // given
        consoleMock = new ConsoleMock();
        consoleMock.addIn( baseName+ "|" + userName + "|" + password);
       StartController.main(new String[0]);

        // when

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

    private void assertOut(String expected, String... parameters) {
        String string = expected.replaceAll("\\n", "\r\n");
        if (parameters.length > 0) {
            string = string.replaceAll("%s", parameters[0]);
        }

//        assertEquals(Arrays.toString(string.toCharArray()), Arrays.toString(consoleMock.getOut().toCharArray()));
        assertEquals(string, consoleMock.getOut());
    }
}
