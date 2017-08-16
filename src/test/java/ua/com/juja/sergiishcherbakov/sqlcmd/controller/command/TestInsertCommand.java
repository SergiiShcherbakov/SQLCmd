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
    private DatabaseManager dBManager;
    private Viewer viewer;
    private Command insertCommand;

    private void setMocks() {
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
        setMocks();
        // when
        boolean isExit;
        Map testTable = new HashMap();
        testTable.put("column1", "value1");
        testTable.put("column2", "value2");
        testTable.put("columnN", "valueN");
        when(dBManager.insertRow("insert", testTable )).thenReturn("Ok");
        isExit = insertCommand.processAndExit(viewer, dBManager, "insert|tab|column1|value1|column2|value2|columnN|valueN");
        // then
        Mockito.verify(dBManager).insertRow("tab", testTable);
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("row \"column1=value1, column2=value2, columnN=valueN\" was added to table \"tab\"");
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithBadString() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        // when
        boolean isExit;
        isExit = insertCommand.processAndExit(viewer, dBManager, "insertt|tab|column1|value1|column2|value2|columnN|valueN");
        // then
        Mockito.verify(dBManager, never()).insertRow(any(), any());
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("\"insert\" parameter are expected but \"insertt\" is entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);

    }

    @Test
    public void canProcessAndExitWithStringLongerThenNeed() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        // when
        boolean isExit;
        isExit = insertCommand.processAndExit(viewer, dBManager, "insert|tab|tab|column1|value1|column2|value2|columnN|valueN");
        // then
        Mockito.verify(dBManager, never()).insertRow(any(), any());
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("insert wrong number of parameters. An even number of parameters are expected and an odd are entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithStringWithoutParameters() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        // when
        boolean isExit;
        isExit = insertCommand.processAndExit(viewer, dBManager, "insert|");
        // then
        Mockito.verify(dBManager, never()).insertRow(any(), any());
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("insert wrong number of parameters. " +
                "Minimum 4 parameters are expected and 1 parameters are entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithoutRows() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        // when
        boolean isExit;
        isExit = insertCommand.processAndExit(viewer, dBManager, "insert|tab");
        // then
        Mockito.verify(dBManager, never()).insertRow(any(), any());
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("insert wrong number of parameters. Minimum 4 parameters are expected and 2 parameters are entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithoutAllParameters() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        // when
        boolean isExit;
        isExit = insertCommand.processAndExit(viewer, dBManager, "insert");
        // then
        Mockito.verify(dBManager, never()).insertRow(any(), any());
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("insert wrong number of parameters. " +
                "Minimum 4 parameters are expected and 1 parameters are entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verifyNoMoreInteractions(viewer);
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
                        "\t\t\tformat the command:" + System.lineSeparator() +
                        "\t\t\tinsert|tableName|column1|value1|column2|value2|...|columnN|ValueN"
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
