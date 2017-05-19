package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by Sergii Shcherbakov on 10.05.2017.
 */
public class TestInsertCommand {
    DatabaseManager dBManager;
    Viewer viewer;
    Command insertCommand;

    private void setMoks() {
        dBManager = mock(DatabaseManager.class);
        viewer = mock(Viewer.class);
    }

    @Before
    public void setCommand(){
        insertCommand = new InsertCommand();
    }

    @Test
    public void canProcessAndExitWithGoodString() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        // when
        boolean isExit = true;
        Map addRowToTable = new HashMap();
        addRowToTable.put("column1", "value1");
        addRowToTable.put("column2", "value2");
        addRowToTable.put("columnN", "valueN");
        when(dBManager.insertRow("insert", addRowToTable )).thenReturn("Ok");
        isExit = insertCommand.processAndExit(viewer, dBManager, "insert|tab|column1|value1|column2|value2|columnN|valueN");

        // then
        Mockito.verify(dBManager).insertRow("tab", addRowToTable);
        Mockito.verify(viewer).write("row \"column1=value1, column2=value2, columnN=valueN\" was added to table \"tab\"");
        assertFalse(isExit);

    }

    @Test
    public void canProcessAndExitWithBadString() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        // when
        boolean isExit = true;
        Map addRowToTable = new HashMap();
        isExit = insertCommand.processAndExit(viewer, dBManager, "insertt|tab|column1|value1|column2|value2|columnN|valueN");

        // then
        Mockito.verify(viewer).write("\"insert\" parameter are expected but \"insertt\" is entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verify(dBManager, never()).insertRow("tab", addRowToTable);
        assertFalse(isExit);

    }

    @Test
    public void canProcessAndExitWithStringLongerThenNeed() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        Map addRowToTable = new HashMap();
        // when
        boolean isExit = true;
        isExit = insertCommand.processAndExit(viewer, dBManager, "insert|tab|tab|column1|value1|column2|value2|columnN|valueN");
        // then
        Mockito.verify(dBManager, never()).insertRow("", addRowToTable);
        Mockito.verify(viewer).write("insert wrong number of parameters. An even number of parameters are expected and an odd are entered");
        Mockito.verify(viewer).write("please, try again");
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithStringWithoutParameters() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        Map addRowToTable = new HashMap();
        // when
        boolean isExit = true;
        isExit = insertCommand.processAndExit(viewer, dBManager, "insert|");
        // then
        Mockito.verify(dBManager, never()).insertRow("", addRowToTable);
        Mockito.verify(viewer).write("insert wrong number of parameters. An even number of parameters are expected and an odd are entered");
        Mockito.verify(viewer).write("please, try again");
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithoutRows() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        Map addRowToTable = new HashMap();
        // when
        boolean isExit = true;
        isExit = insertCommand.processAndExit(viewer, dBManager, "insert|tab");
        // then
        Mockito.verify(dBManager, never()).insertRow("", addRowToTable);
        Mockito.verify(viewer).write("insert wrong number of parameters. Minimum 4 are expected and 2 are entered");
        Mockito.verify(viewer).write("please, try again");
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithoutAllParameters() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        Map addRowToTable = new HashMap();
        // when
        boolean isExit = true;
        isExit = insertCommand.processAndExit(viewer, dBManager, "insert");
        // then
        Mockito.verify(dBManager, never()).insertRow("", addRowToTable);
        Mockito.verify(viewer).write("insert wrong number of parameters. An even number of parameters are expected and an odd are entered");
        Mockito.verify(viewer).write("please, try again");
        assertFalse(isExit);
    }

    @Test
    public void getName(){
        // given
        // when
        String name = insertCommand.getName();
        // then
        assertEquals("insert", name);
    }

    @Test
    public void getDescriptionTest(){
        // given
        // when
        String name = insertCommand.getDescription();
        // then
        assertEquals("insert\t\tinsert row into table specified by user" + System.lineSeparator() +
                "\t\tformat the command:" + System.lineSeparator() +
                "\t\t insert|\"table name\"|\"column1\"|\"value1\"|\"column2\"|\"value2\"|...|\"columnN\"|\"ValueN\""
                 , name);
    }

    @Test
    public void canProcessWithBigString(){
        // given
        // when
        boolean canProcess = insertCommand.canProcess("insert");
        // then
        assertTrue(canProcess);
    }
}
