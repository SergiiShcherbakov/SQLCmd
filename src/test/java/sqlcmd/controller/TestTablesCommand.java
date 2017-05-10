package sqlcmd.controller;

import org.junit.Test;
import ua.com.juja.sergiishcherbakov.sqlcmd.controller.command.Command;
import ua.com.juja.sergiishcherbakov.sqlcmd.controller.command.TablesCommand;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLException;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;

/**
 * Created by Sergii Shcherbakov on 10.05.2017.
 */
public class TestTablesCommand {

    @Test
    public void canProcessAndExitWithGoodString(){
        // given
        DatabaseManager dBManager =  mock(DatabaseManager.class);
        Viewer viewer =  mock(Viewer.class);
        Command tablesCommand = new TablesCommand();
        // when
        boolean isExit = true;
        try {
             isExit = tablesCommand.processAndExit(viewer, dBManager, "tables");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // then
        assertFalse(isExit);
    }

    @Test
    public void getName(){
        // given
        Command tablesCommand = new TablesCommand();
        // when
        String name = tablesCommand.getName();
        // then
        assertEquals("tables", name);
    }

    @Test
    public void getDescriptionTest(){
        // given
        Command tablesCommand = new TablesCommand();
        // when
        String name = tablesCommand.getDescription();
        // then
        assertEquals("tables\t\tdisplays all tables in the database", name);
    }

    @Test
    public void canProcessWithBigString(){
        // given
        Command tablesCommand = new TablesCommand();
        // when
        boolean canProcess = tablesCommand.canProcess("tab");
        // then
        assertFalse(canProcess);
    }

    @Test
    public void canProcessBadBigString(){
        // given
        Command tablesCommand = new TablesCommand();
        // when
        boolean canProcess = tablesCommand.canProcess("tabless");
        // then
        assertFalse(canProcess);
    }

    @Test
    public void canProcessWithGoodString(){
        // given
        Command tablesCommand = new TablesCommand();
        // when
        boolean canProcess = tablesCommand.canProcess("tables");
        // then
        assertTrue(canProcess);
    }

    @Test
    public void canProcessWithGoodUpperCaseString(){
        // given
        Command tablesCommand = new TablesCommand();
        // when
        boolean canProcess = tablesCommand.canProcess("TABLES");
        // then
        assertTrue(canProcess);
    }


}
