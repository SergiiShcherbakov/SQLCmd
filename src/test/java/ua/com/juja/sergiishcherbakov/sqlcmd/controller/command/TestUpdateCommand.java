package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Sergii Shcherbakov on 10.05.2017.
 */
public class TestUpdateCommand {
    DatabaseManager dBManager;
    Viewer viewer;
    Command updateCommand;

    private void setMocks() {
        dBManager = mock(DatabaseManager.class);
        viewer = mock(Viewer.class);
    }

    @Before
    public void setCommand(){
        updateCommand = new UpdateCommand();
    }

    @Test
    public void canProcessAndExitWithGoodString() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        boolean isExit = true;
        Pair<String, String> where = new Pair<>("id", "1");
        HashMap<String, String> change = new HashMap<>();
        change.put("login" , "vasya");
        change.put("password" , "tttttt");
        List<List<String>> table = new LinkedList<>();
        // when
        when(dBManager.updateTable("tab", where, change)).thenReturn(true);
        when(dBManager.selectAllFromTable("tab")).thenReturn(table);
        isExit = updateCommand.processAndExit(viewer, dBManager,"update|tab|id|1|login|vasya|password|tttttt" );
        // then
        Mockito.verify(dBManager).updateTable("tab", where, change);
        Mockito.verify(viewer).write("in table \"tab\" was updated row(s) with column \"id\"=\"1\"");
        Mockito.verify(dBManager).selectAllFromTable("tab");
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).printTable(table);
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithBadString() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        boolean isExit = true;
        // when
        isExit = updateCommand.processAndExit(viewer, dBManager,"updatee|tab|id|1|login|vasya" );
        // then
        Mockito.verify(dBManager, never()).updateTable(any(), any(), any());
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("\"update\" parameter are expected but \"updatee\" is entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verify(viewer, never()).printTable(any());
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithoutOneParameterWhenNeedChange() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        boolean isExit = true;
        // when
        isExit = updateCommand.processAndExit(viewer, dBManager,"update|tab|id|1|login|vasya|password" );
        // then
        Mockito.verify(dBManager, never()).updateTable(any(), any(), any());
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("insert wrong number of parameters. " +
                "An even number of parameters are expected and an odd are entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verify(dBManager, never()).selectAllFromTable(any());
        Mockito.verify(viewer, never()).printTable(any());
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithStringWithoutParametersToChangeData() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        boolean isExit = true;
        // when
        isExit = updateCommand.processAndExit(viewer, dBManager,"update|tab|id|1" );
        // then
        Mockito.verify(dBManager, never()).updateTable(any(), any(), any());
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("insert wrong number of parameters. " +
                "Minimum 6 parameters are expected and 4 parameters are entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithoutAllParameters() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        boolean isExit = true;
        // when
        isExit = updateCommand.processAndExit(viewer, dBManager,"update" );
        // then
        Mockito.verify(dBManager, never()).updateTable(any(), any(), any());
        Mockito.verify(viewer).write("insert wrong number of parameters. " +
                "Minimum 6 parameters are expected and 1 parameters are entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verify(dBManager, never()).selectAllFromTable(any());
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer, never()).printTable(any());
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void getName(){
        // given
        // when
        String name = updateCommand.getName();
        // then
        assertEquals("update", name);
    }

    @Test
    public void getDescriptionTest(){
        // given
        // when
        String name = updateCommand.getDescription();
        // then
        assertEquals("update\t\tupdate table where column 1 = value1 " + System.lineSeparator() +
                "\t\t\twith values 2 ... value n in column 2 - column n specified by user" + System.lineSeparator() +
                "\t\t\tformat the command:" + System.lineSeparator() +
                "\t\t\tupdate|tableName|column1|value1|column2|value2|...|columnN|valueN" , name);
    }

    @Test
    public void canProcessWithBigString(){
        // given
        // when
        boolean canProcess = updateCommand.canProcess("updatee");
        // then
        assertTrue(canProcess);
    }
}
