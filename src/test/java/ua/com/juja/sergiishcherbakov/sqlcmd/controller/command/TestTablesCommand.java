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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

/**
 * Created by Sergii Shcherbakov on 10.05.2017.
 */
public class TestTablesCommand {
    DatabaseManager dBManager;
    Viewer viewer;
    Command tablesCommand;

    private void setMoks() {
        dBManager = mock(DatabaseManager.class);
        viewer = mock(Viewer.class);
    }

    @Before
    public void setCommand(){
        tablesCommand = new TablesCommand();
    }
    @Test
    public void canProcessAndExitWithGoodString() throws SQLException, ClassNotFoundException {
        // given
        tablesCommand = new TablesCommand();
        setMoks();
        List<String> responce = new LinkedList<>();
        responce.add("User");
        responce.add("Bugs");
        // when
        boolean isExit = true;
        when(dBManager.getTablesNames()).thenReturn(responce);
        isExit = tablesCommand.processAndExit(viewer, dBManager, "tables");
        // then
        Mockito.verify(dBManager).getTablesNames();
        Mockito.verify(viewer).write("[User, Bugs]");
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithStringLongerThenNeed() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        // when
        boolean isExit = true;
        isExit = tablesCommand.processAndExit(viewer, dBManager, "tabless");

        // then
        Mockito.verify(dBManager, never()).getTablesNames();
        Mockito.verify(viewer).write("tables cant be printed because \"tables\" " +
                "parameter are expected but \"tabless\" is entered");
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithStringWithoutParameters() throws SQLException, ClassNotFoundException {
        // given
        setMoks();
        // when
        boolean isExit = true;
        isExit = tablesCommand.processAndExit(viewer, dBManager, "");
        // then
        Mockito.verify(dBManager, never()).getTablesNames();
        Mockito.verify(viewer).write("tables cant be printed because \"tables\" parameter are expected but \"\" is entered");
        assertFalse(isExit);
    }

    @Test
    public void getName(){
        // given
        // when
        String name = tablesCommand.getName();
        // then
        assertEquals("tables", name);
    }

    @Test
    public void getDescriptionTest(){
        // given
        // when
        String description = tablesCommand.getDescription();
        // then
        assertEquals("tables\t\tdisplays all tables in the database"
                , description);
    }

    @Test
    public void canProcessWithGoodString(){
        // given
        // when
        boolean canProcess = tablesCommand.canProcess("tables");
        // then
        assertTrue(canProcess);
    }

    @Test
    public void canProcessWithBagString(){
        // given
        // when
        boolean canProcess = tablesCommand.canProcess("exitt");
        // then
        assertFalse(canProcess);
    }

}
