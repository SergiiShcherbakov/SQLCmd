package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by Sergii Shcherbakov on 10.05.2017.
 */
public class TestUnsupportedCommand {
    DatabaseManager dBManager;
    Viewer viewer;
    Command updateCommand;

    private void setMocks() {
        dBManager = mock(DatabaseManager.class);
        viewer = mock(Viewer.class);
    }

    @Before
    public void setCommand(){
        updateCommand = new UnsupportedCommand();
    }

    @Test
    public void canProcessAndExitWithBadString() {
        // given
        setMocks();
        boolean isExit = true;
        // when
        isExit = updateCommand.processAndExit(viewer, dBManager,"khaksfhlahsf" );
        // then
        Mockito.verifyNoMoreInteractions(dBManager);
        Mockito.verify(viewer).write("Command \"khaksfhlahsf\" does not supported.");
//        Mockito.verify(viewer).write("please, try again");
        Mockito.verifyNoMoreInteractions(viewer);
        assertFalse(isExit);
    }


    @Test
    public void getName(){
        // given
        // when
        String name = updateCommand.getName();
        // then
        assertEquals("", name);
    }

    @Test
    public void getDescriptionTest(){
        // given
        // when
        String name = updateCommand.getDescription();
        // then
        assertEquals("\t" , name);
    }

    @Test
    public void canProcessWithBadString(){
        // given
        // when
        boolean canProcess = updateCommand.canProcess("jhgjggk");
        // then
        assertTrue(canProcess);
    }
}
