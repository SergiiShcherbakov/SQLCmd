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
public class TestFindCommand {
    DatabaseManager dBManager;
    Viewer viewer;
    Command dropCommand;

    private void setMoks() {
        dBManager = mock(DatabaseManager.class);
        viewer = mock(Viewer.class);
    }

    @Before
    public void setCommand(){
        dropCommand = new DropCommand();
    }

    @Test
    public void canProcessAndExitWithGoodString() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        // when
        boolean isExit = true;
        isExit = dropCommand.processAndExit(viewer, dBManager, "drop|tab");

        // then
        Mockito.verify(dBManager).deleteTable("tab");
        Mockito.verify(viewer).write("tab was removed");
        assertFalse(isExit);

    }

    @Test
    public void canProcessAndExitWithBadString() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        // when
        boolean isExit = true;
        isExit = dropCommand.processAndExit(viewer, dBManager, "drop1|tab");

        // then
        Mockito.verify(viewer).write("\"drop\" parameter are expected but \"drop1\" is entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verify(dBManager, never()).deleteTable("tab");
        assertFalse(isExit);

    }

    @Test
    public void canProcessAndExitWithStringLongerThenNeed() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        // when
        boolean isExit = true;
        isExit = dropCommand.processAndExit(viewer, dBManager, "drop|tab|tab");
        // then
        Mockito.verify(dBManager, never()).deleteTable("");
        Mockito.verify(viewer).write("2 parameters are expected but 3 is entered");
        Mockito.verify(viewer).write("please, try again");
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithStringWithoutParameters() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        // when
        boolean isExit = true;
        isExit = dropCommand.processAndExit(viewer, dBManager, "drop|");
        // then
        Mockito.verify(dBManager, never()).deleteTable("");
        Mockito.verify(viewer).write("2 parameters are expected but 1 is entered");
        Mockito.verify(viewer).write("please, try again");
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithoutAllParameters() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        // when
        boolean isExit = true;
        isExit = dropCommand.processAndExit(viewer, dBManager, "drop");
        // then
        Mockito.verify(dBManager, never()).deleteTable("");
        Mockito.verify(viewer).write("2 parameters are expected but 1 is entered");
        Mockito.verify(viewer).write("please, try again");
        assertFalse(isExit);
    }

    @Test
    public void getName(){
        // given
        // when
        String name = dropCommand.getName();
        // then
        assertEquals("drop", name);
    }

    @Test
    public void getDescriptionTest(){
        // given
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
        // when
        boolean canProcess = dropCommand.canProcess("drop");
        // then
        assertTrue(canProcess);
    }
}
