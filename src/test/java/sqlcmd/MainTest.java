package sqlcmd;

import org.junit.Test;
import ua.com.juja.sergiishcherbakov.sqlcmd.Main;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
/**
 * Created by StrannikFujitsu on 20.04.2017.
 */

public class MainTest {

    private ConsoleMock console = new ConsoleMock();

    @Test
    public void TestMain() throws SQLException, ClassNotFoundException {
        // given


        // when
        Main.main(new String[0]);
//
        // then
//        assertOut("-------- PostgreSQL JDBC Connection Testing ------------\n" +
//                "PostgreSQL JDBC Driver Registered!\n" +
//                "You made it, take control your database now!\n");
    }

    private void assertOut(String expected, String... parameters) {
        String string = expected.replaceAll("\\n", "\r\n");
        if (parameters.length > 0) {
            string = string.replaceAll("%s", parameters[0]);
        }
        assertEquals(string, console.getOut());
    }
}