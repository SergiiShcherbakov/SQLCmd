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
import static org.mockito.Mockito.*;

/**
 * Created by Sergii Shcherbakov on 10.05.2017.
 */
public class TestHelpCommand {
    DatabaseManager dBManager;
    Viewer viewer;
    HelpMenuCommand helpCommand;

    private void setMoks() {
        dBManager = mock(DatabaseManager.class);
        viewer = mock(Viewer.class);

        List<Command> commands = new LinkedList<>();
        for ( int i = 0; i <5 ; i++) {
            Command tempCommand = mock( Command.class);
            when( tempCommand.getDescription()).thenReturn( "test description" );
            commands.add( tempCommand );
        }
        helpCommand.setCommand(commands);
    }

    @Before
    public void setCommand(){
        helpCommand = new HelpMenuCommand();
    }
    @Test
    public void canProcessAndExitWithGoodStringAndFiveCommands() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        // when
        boolean isExit = true;
        isExit = helpCommand.processAndExit(viewer, dBManager, "help");
        // then
        Mockito.verify(viewer).write("the program support next command:");
        Mockito.verify(viewer, times(5)).write("test description");
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithStringLongerThenNeed() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        // when
        boolean isExit = true;
        isExit = helpCommand.processAndExit(viewer, dBManager, "helpp");
        // then
        Mockito.verify(viewer).write("\"help\" parameter are expected but \"helpp\" is entered");
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithStringWithoutParameters() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        // when
        boolean isExit = true;
        isExit = helpCommand.processAndExit(viewer, dBManager, "");
        // then
        Mockito.verify(viewer).write("\"help\" parameter are expected but \"\" is entered");
        assertFalse(isExit);
    }

    @Test
    public void getName(){
        // given
        // when
        String name = helpCommand.getName();
        // then
        assertEquals("help", name);
    }

    @Test
    public void getDescriptionTest(){
        // given
        // when
        String description = helpCommand.getDescription();
        // then
        assertEquals("help\t\tget name and description of command that support the program"
                , description);
    }

    @Test
    public void canProcessWithGoodString(){
        // given
        // when
        boolean canProcess = helpCommand.canProcess("help");
        // then
        assertTrue(canProcess);
    }

    @Test
    public void canProcessWithBagString(){
        // given
        // when
        boolean canProcess = helpCommand.canProcess("helpp");
        // then
        assertFalse(canProcess);
    }
}
