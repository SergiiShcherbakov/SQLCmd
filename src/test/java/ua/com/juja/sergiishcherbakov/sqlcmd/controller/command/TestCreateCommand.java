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
public class TestCreateCommand {
    private DatabaseManager dBManager;
    private Viewer viewer;
    private Command createCommand;

    private void setMocks() {
        dBManager = mock(DatabaseManager.class);
        viewer = mock(Viewer.class);
    }

    @Before
    public void setCommand(){
        createCommand = new CreateCommand();
    }

    @Test
    public void canProcessAndExitWithGoodString() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        boolean isExit;
        List<String> addColumn = new LinkedList<>();
        addColumn.add("column1");
        addColumn.add("column2");
        addColumn.add("columnN");
        // when
        when(dBManager.createTableWithoutTypesFields("tab", addColumn )).thenReturn(true);
        isExit = createCommand.processAndExit(viewer, dBManager, "create|tab|column1|column2|columnN");
        // then
        Mockito.verify(dBManager).createTableWithoutTypesFields("tab", addColumn);
        Mockito.verify(viewer).write("table with name \"tab\" and with column \"column1, column2, columnN\" " +
                "added to current database");
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithBadCommandName() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        boolean isExit;
        // when
        isExit = createCommand.processAndExit(viewer, dBManager, "createe|tab|column1|column2|columnN");
        // then
        Mockito.verify(viewer).write("\"create\" parameter are expected but \"createe\" is entered");
        Mockito.verify(viewer).write("please, try again");
        Mockito.verify(dBManager, never()).createTableWithoutTypesFields(any(), any());
        assertFalse(isExit);

    }

    @Test
    public void canProcessAndExitWithStringWithoutParameters() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        // when
        boolean isExit;
        isExit = createCommand.processAndExit(viewer, dBManager, "create|");
        // then
        Mockito.verify(dBManager, never()).createTableWithoutTypesFields(any(), any());
        Mockito.verify(viewer).write("Insert wrong number of parameters. Minimum 3 parameters " +
                "are expected but 1 parameters are entered");
        Mockito.verify(viewer).write("please, try again");
        assertFalse(isExit);
    }

    @Test
    public void canProcessAndExitWithStringWithoutColumns() throws SQLException, ClassNotFoundException {
        // given
        setMocks();
        // when
        boolean isExit;
        isExit = createCommand.processAndExit(viewer, dBManager, "create|tab");
        // then
        Mockito.verify(dBManager, never()).createTableWithoutTypesFields(any(), any());
        Mockito.verify(viewer).write("Insert wrong number of parameters. Minimum 3 parameters " +
                "are expected but 2 parameters are entered");
        Mockito.verify(viewer).write("please, try again");
        assertFalse(isExit);
    }

    @Test
    public void getName(){
        // given
        // when
        String name = createCommand.getName();
        // then
        assertEquals("create", name);
    }

    @Test
    public void getDescriptionTest(){
        // given
        // when
        String name = createCommand.getDescription();
        // then
        assertEquals("create\t\tcreate new table specified by user" + System.lineSeparator() +
                        "\t\t\tformat the command:" + System.lineSeparator() +
                        "\t\t\tcreate|tableName|column1|column2|...|columnN"
                , name);
    }

    @Test
    public void canProcessWithBigString(){
        // given
        // when
        boolean canProcess = createCommand.canProcess("createe");
        // then
        assertTrue(canProcess);
    }
}
