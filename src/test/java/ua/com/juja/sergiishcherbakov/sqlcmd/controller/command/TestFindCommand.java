package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

/**
 * Created by Sergii Shcherbakov on 10.05.2017.
 */
public class TestFindCommand {
    DatabaseManager dBManager;
    Viewer viewer;
    Command findCommand;

    private void setMocks() {
        dBManager = mock(DatabaseManager.class);
        viewer = mock(Viewer.class);
    }

    @Before
    public void setCommand(){
        findCommand = new FindCommand();
    }

    @Test
    public void canProcessAndExitWithGoodString() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        boolean isExit;
        List<List<String>> table = new LinkedList<>();
        // when
        Mockito.when(dBManager.selectAllFromTable("tab")).thenReturn(table);
        isExit = findCommand.processAndExit(viewer, dBManager, "find|tab");
        // then
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
        boolean isExit;
        // when
        isExit = findCommand.processAndExit(viewer, dBManager, "find1|tab");
        // then
        Mockito.verify(dBManager, never()).selectAllFromTable(any());
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("\"find\" parameter are expected but \"find1\" is entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithStringLongerThenNeed() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        boolean isExit;
        // when
        isExit = findCommand.processAndExit(viewer, dBManager, "find|tab|tab");
        // then
        Mockito.verify(dBManager, never()).selectAllFromTable(any());
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("2 parameters are expected but 3 is entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithStringWithoutParameters() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        boolean isExit;
        // when
        isExit = findCommand.processAndExit(viewer, dBManager, "find|");
        // then
        Mockito.verify(dBManager, never()).selectAllFromTable("");
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("2 parameters are expected but 1 is entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithoutAllParameters() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        boolean isExit;
        // when
        isExit = findCommand.processAndExit(viewer, dBManager, "find");
        // then
        Mockito.verify(dBManager, never()).selectAllFromTable("");
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("2 parameters are expected but 1 is entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void getName(){
        // given
        // when
        String name = findCommand.getName();
        // then
        assertEquals("find", name);
    }

    @Test
    public void getDescriptionTest(){
        // given
        // when
        String name = findCommand.getDescription();
        // then
        assertEquals("find\t\tfind and print tables specified by user"+ System.lineSeparator() +
                "\t\t\tformat the command:"+ System.lineSeparator() +
                "\t\t\tfind|tableName" , name);
    }

    @Test
    public void canProcessWithBigString(){
        // given
        // when
        boolean canProcess = findCommand.canProcess("find");
        // then
        assertTrue(canProcess);
    }
}
