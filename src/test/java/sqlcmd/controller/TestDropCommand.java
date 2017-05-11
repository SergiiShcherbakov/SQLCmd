package sqlcmd.controller;

import org.junit.Test;
import org.mockito.Mockito;
import ua.com.juja.sergiishcherbakov.sqlcmd.controller.command.Command;
import ua.com.juja.sergiishcherbakov.sqlcmd.controller.command.DropCommand;
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
public class TestDropCommand {
        DatabaseManager dBManager;
        Viewer viewer;
        Command dropCommand;

    @Test
    public void canProcessAndExitWithGoodString() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        dropCommand = new DropCommand();
        // when
        boolean isExit = true;
        isExit = dropCommand.processAndExit(viewer, dBManager, "drop|tab");

        // then
        Mockito.verify(dBManager).deleteTable("tab");
        Mockito.verify(viewer).write("tab was removed");
        assertFalse(isExit);

    }

    private void setMoks() {
        dBManager = mock(DatabaseManager.class);
        viewer = mock(Viewer.class);
    }

    @Test
    public void canProcessAndExitWithStringLongerThenNeed() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        Command dropCommand = new DropCommand();
        // when
        boolean isExit = true;
        isExit = dropCommand.processAndExit(viewer, dBManager, "drop|tab|tab");
        // then
        Mockito.verify(dBManager, never()).deleteTable("");
        Mockito.verify(viewer).write("2 parameters are expected but 3 is entered please, try again");
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithStringWithoutParameters() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        Command dropCommand = new DropCommand();
        // when
        boolean isExit = true;
        isExit = dropCommand.processAndExit(viewer, dBManager, "drop|");
        // then
        Mockito.verify(dBManager, never()).deleteTable("");
        Mockito.verify(viewer).write("2 parameters are expected but 1 is entered please, try again");
        assertFalse(isExit);
    }

  @Test
    public void canProcessAndExitWithoutAllParameters() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        Command dropCommand = new DropCommand();
        // when
        boolean isExit = true;
        isExit = dropCommand.processAndExit(viewer, dBManager, "drop");
        // then
        Mockito.verify(dBManager, never()).deleteTable("");
        Mockito.verify(viewer).write("2 parameters are expected but 1 is entered please, try again");
        assertFalse(isExit);
    }

    @Test
    public void getName(){
        // given
        Command dropCommand = new DropCommand();
        // when
        String name = dropCommand.getName();
        // then
        assertEquals("drop", name);
    }

    @Test
    public void getDescriptionTest(){
        // given
        Command dropCommand = new DropCommand();
        // when
        String name = dropCommand.getDescription();
        // then
        assertEquals("drop\t\tremove tables specified by user" + System.lineSeparator()+
                "\t\tformat the command:" + System.lineSeparator()+
                "\t\t drop|\"table name\"", name);
    }

    @Test
    public void canProcessWithBigString(){
        // given
        Command dropCommand = new DropCommand();
        // when
        boolean canProcess = dropCommand.canProcess("drop");
        // then
        assertTrue(canProcess);
    }

}
