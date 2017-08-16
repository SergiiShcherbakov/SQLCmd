package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLException;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Sergii Shcherbakov on 10.05.2017.
 */
public class TestClearCommand {
    private DatabaseManager dBManager;
    private Viewer viewer;
    private Command clearCommand;

    private void setMocks() {
        dBManager = mock(DatabaseManager.class);
        viewer = mock(Viewer.class);
    }

    @Before
    public void setCommand(){
        clearCommand = new ClearCommand();
    }

    @Test
    public void canProcessAndExitWithGoodString() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        // when
        boolean isExit;
        when(dBManager.clearTable("tab")).thenReturn(true);
        isExit = clearCommand.processAndExit(viewer, dBManager, "clear|tab");
        // then
        Mockito.verify(dBManager).clearTable("tab");
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("table tab was cleared");
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithBadString() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        // when
        boolean isExit;
        isExit = clearCommand.processAndExit(viewer, dBManager, "clear1|tab");
        // then
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("\"clear\" parameter are expected but \"clear1\" is entered");
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
        isExit = clearCommand.processAndExit(viewer, dBManager, "clear|tab|tab");
        // then
        Mockito.verify(viewer).write("2 parameters are expected but 3 is entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithStringWithoutParameters() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        // when
        boolean isExit;
        isExit = clearCommand.processAndExit(viewer, dBManager, "clear|");
        // then
        Mockito.verify(viewer).write("2 parameters are expected but 1 is entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithoutAllParameters() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        // when
        boolean isExit;
        isExit = clearCommand.processAndExit(viewer, dBManager, "clear");
        // then
        Mockito.verify(viewer).write("2 parameters are expected but 1 is entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void getName(){
        // given
        // when
        String name = clearCommand.getName();
        // then
        assertEquals("clear", name);
    }

    @Test
    public void getDescriptionTest(){
        // given
        // when
        String name = clearCommand.getDescription();
        // then
        assertEquals("clear\t\tclear table specified by user" + System.lineSeparator() +
                "\t\t\tformat the command:" + System.lineSeparator() +
                "\t\t\tclear|tableName" , name);
    }

    @Test
    public void canProcessWithBigString(){
        // given
        // when
        boolean canProcess = clearCommand.canProcess("clear");
        // then
        assertTrue(canProcess);
    }
}
