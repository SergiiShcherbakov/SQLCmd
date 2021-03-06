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
import static org.mockito.Mockito.never;

/**
 * Created by Sergii Shcherbakov on 10.05.2017.
 */
public class TestExitCommand {
    private DatabaseManager dBManager;
    private Viewer viewer;
    private Command exitCommand;

    private void setMocks() {
        dBManager = mock(DatabaseManager.class);
        viewer = mock(Viewer.class);
    }

    @Before
    public void setCommand(){
        exitCommand = new ExitCommand();
    }
    @Test
    public void canProcessAndExitWithGoodString() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        // when
        boolean isExit;
        isExit = exitCommand.processAndExit(viewer, dBManager, "exit");
        // then
        Mockito.verify(dBManager).closeConnection();
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("good by, see you soon.");
        Mockito.verifyNoMoreInteractions(viewer);
        assertTrue(isExit);
    }

    @Test
    public void canProcessAndExitWithStringLongerThenNeed() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        // when
        boolean isExit;
        isExit = exitCommand.processAndExit(viewer, dBManager, "exitt");
        // then
        Mockito.verify(dBManager, never()).closeConnection();
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("\"exit\" parameter are expected but \"exitt\" is entered");
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
        isExit = exitCommand.processAndExit(viewer, dBManager, "exitt");
        // then
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("\"exit\" parameter are expected but \"exitt\" is entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }

    @Test
    public void getName(){
        // given
        // when
        String name = exitCommand.getName();
        // then
        assertEquals("exit", name);
    }

    @Test
    public void getDescriptionTest(){
        // given
        // when
        String description = exitCommand.getDescription();
        // then
        assertEquals("exit\t\texit from the program"
                , description);
    }

    @Test
    public void canProcessWithGoodString(){
        // given
        // when
        boolean canProcess = exitCommand.canProcess("exit");
        // then
        assertTrue(canProcess);
    }

    @Test
    public void canProcessWithBagString(){
        // given
        // when
        boolean canProcess = exitCommand.canProcess("exitt");
        // then
        assertFalse(canProcess);
    }

}
