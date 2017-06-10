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
public class TestDeleteCommand {
    DatabaseManager dBManager;
    Viewer viewer;
    Command deleteCommand;

    private void setMoks() {
        dBManager = mock(DatabaseManager.class);
        viewer = mock(Viewer.class);
    }

    @Before
    public void setCommand(){
        deleteCommand = new DeleteCommand();
    }

    @Test
    public void canProcessAndExitWithGoodString() throws SQLException, ClassNotFoundException, SQLException {
        // given
        setMoks();
        boolean isExit = true;
        // when
        isExit = deleteCommand.processAndExit(viewer, dBManager, "delete|tab|row1|Value1");
        // then
        Mockito.verify(dBManager).deleteRowFromTable("tab", "row1", "Value1") ;
        Mockito.verify(viewer).write("row with  value \"Value1\" in field \"row1\" was removed from table \"tab\"");
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithBadString() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        boolean isExit = true;
        // when
        isExit = deleteCommand.processAndExit(viewer, dBManager, "deletee|tab|row1|Value1");
        // then
        Mockito.verify(dBManager, never()).deleteRowFromTable(any(), any(), any()) ;
        Mockito.verify(viewer).write("\"delete\" parameter are expected but \"deletee\" is entered");
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithStringLongerThenNeed() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        boolean isExit = true;
        // when
        isExit = deleteCommand.processAndExit(viewer, dBManager, "delete|tab|row1|Value1|Value2");
        // then
        Mockito.verify(dBManager, never()).deleteRowFromTable(any(), any(), any()) ;
        Mockito.verify(viewer).write("4 parameters are expected but 5 is entered");
        assertFalse(isExit);
    }

//    @Test
//    public void canProcessAndExitWithStringWithoutParameters() throws SQLException, ClassNotFoundException {
//        // given
//        setMoks();
//        boolean isExit = true;
//        // when
//        isExit = deleteCommand.processAndExit(viewer, dBManager, "find|");
//        // then
//        Mockito.verify(dBManager, never()).selectAllFromTable("");
//        Mockito.verify(viewer).write("2 parameters are expected but 1 is entered");
//        Mockito.verify(viewer).write("please, try again");
//        assertFalse(isExit);
//    }
//
//    @Test
//    public void canProcessAndExitWithoutAllParameters() throws SQLException, ClassNotFoundException {
//        // given
//        setMoks();
//        boolean isExit = true;
//        // when
//        isExit = deleteCommand.processAndExit(viewer, dBManager, "find");
//        // then
//        Mockito.verify(dBManager, never()).selectAllFromTable("");
//        Mockito.verify(viewer).write("2 parameters are expected but 1 is entered");
//        Mockito.verify(viewer).write("please, try again");
//        assertFalse(isExit);
//    }

    @Test
    public void getName(){
        // given
        // when
        String name = deleteCommand.getName();
        // then
        assertEquals("delete", name);
    }

    @Test
    public void getDescriptionTest(){
        // given
        // when
        String name = deleteCommand.getDescription();
        // then
        assertEquals("delete\t\tdelete row from table by name and value specified by user "+ System.lineSeparator() +
                "\t\tformat the command:"+ System.lineSeparator() +
                "\t\t delete|tableName|column|value" , name);
    }

    @Test
    public void canProcessWithBigString(){
        // given
        // when
        boolean canProcess = deleteCommand.canProcess("deletee");
        // then
        assertTrue(canProcess);
    }
}
