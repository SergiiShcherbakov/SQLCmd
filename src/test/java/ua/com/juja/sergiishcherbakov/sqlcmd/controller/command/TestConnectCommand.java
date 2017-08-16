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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

/**
 * Created by Sergii Shcherbakov on 10.05.2017.
 */
public class TestConnectCommand {
    private DatabaseManager dBManager;
    private Viewer viewer;
    private Command connectCommand;

    private void setMocks() {
        dBManager = mock(DatabaseManager.class);
        viewer = mock(Viewer.class);
    }

    @Before
    public void setCommand(){
        connectCommand = new ConnectCommand();
    }

    @Test
    public void canProcessAndExitWithGoodString() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        // when
        boolean isExit;
        Mockito.when(dBManager.setConnection("SQLCmd", "postgres" ,"z")).thenReturn(true);
        isExit = connectCommand.processAndExit(viewer, dBManager, "connect|SQLCmd|postgres|z");
        // then
        Mockito.verify(dBManager).closeConnection();
        Mockito.verify(dBManager).setConnection("SQLCmd", "postgres" ,"z");
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("connection to database SQLCmd is successful");
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithBadString() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        // when
        boolean isExit;
        isExit = connectCommand.processAndExit(viewer, dBManager, "connectt|SQLCmd|postgres|z");
        // then
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("\"connect\" parameter are expected but \"connectt\" is entered");
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
        isExit = connectCommand.processAndExit(viewer, dBManager, "connect|SQLCmd|postgres|z|z");
        // then
        Mockito.verify(dBManager, never()).closeConnection();
        Mockito.verify(dBManager, never()).setConnection(any(), any(), any());
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("4 parameters are expected but 5 is entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithoutOneParameter() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        // when
        boolean isExit;
        isExit = connectCommand.processAndExit(viewer, dBManager, "connect|postgres|z");
        // then
        Mockito.verify(dBManager, never()).closeConnection();
        Mockito.verify(dBManager, never()).setConnection(any(), any(), any());
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("4 parameters are expected but 3 is entered");
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
        isExit = connectCommand.processAndExit(viewer, dBManager, "connect|");
        // then
        Mockito.verify(dBManager, never()).closeConnection();
        Mockito.verify(dBManager, never()).setConnection(any(), any(), any());
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("4 parameters are expected but 1 is entered");
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
        isExit = connectCommand.processAndExit(viewer, dBManager, "drop");
        // then
        Mockito.verify(dBManager, never()).closeConnection();
        Mockito.verify(dBManager, never()).setConnection(any(), any(), any());
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("4 parameters are expected but 1 is entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void getName(){
        // given
        // when
        String name = connectCommand.getName();
        // then
        assertEquals("connect", name);
    }

    @Test
    public void getDescriptionTest(){
        // given
        // when
        String name = connectCommand.getDescription();
        // then
        assertEquals("connect\t	connect to database specified by user"+ System.lineSeparator() +
                "\t\t\tformat the command:" + System.lineSeparator() +
                "\t\t\tconnect|database|user|password", name);
    }

    @Test
    public void canProcessWithBigString(){
        // given
        // when
        boolean canProcess = connectCommand.canProcess("connectt");
        // then
        assertTrue(canProcess);
    }
}
