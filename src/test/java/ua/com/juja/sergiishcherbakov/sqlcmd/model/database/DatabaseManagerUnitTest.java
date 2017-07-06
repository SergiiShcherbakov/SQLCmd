package ua.com.juja.sergiishcherbakov.sqlcmd.model.database;

import javafx.util.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.*;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Sergii Shcherbakov on 29.05.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DatabaseManagerUnitTest {
    @Mock
    ConnectionController connectionMock = Mockito.mock(ConnectionController.class);

    @InjectMocks
    JDBCPostgresSQLDatabaseManager dbManager = new JDBCPostgresSQLDatabaseManager();

    @Test
    public void goodUpdateCommandByIdwWith2Parameters() throws SQLException, ClassNotFoundException {
        //given
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);

        when(connectionMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        String goodSQL = "UPDATE public.user SET password='tttt', login='petya' WHERE id=1 ;";
        when(statement.execute(goodSQL)).thenReturn(true);

        String table = "user";
        Pair<String, String> idColumn = new Pair<>("id", "1");
        Map<String, String> changes = new HashMap<>();
        changes.put("login", "petya");
        changes.put("password", "tttt");
        boolean answer = false;
        //when
        answer = dbManager.updateTable(table, idColumn, changes);
        //then
        Mockito.verify(statement).execute(goodSQL);
        assertTrue(answer);
    }

    @Test
    public void goodUpdateCommandByIdwWith1Parameters() throws SQLException, ClassNotFoundException {
        //given
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);

        when(connectionMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        String goodSQL = "UPDATE public.temppp SET login='stiven' WHERE id=7 ;";
        when(statement.execute(goodSQL)).thenReturn(true);

        String table = "temppp";
        Pair<String, String> idColumn = new Pair<>("id", "7");
        Map<String, String> changes = new HashMap<>();
        changes.put("login", "stiven");
        boolean answer = false;
        //when
        answer = dbManager.updateTable(table, idColumn, changes);
        //then
        Mockito.verify(statement).execute(goodSQL);
        assertTrue(answer);
    }

    @Test
    public void goodCreateTableWithoutTypeFieldsWith2Columns() throws SQLException, ClassNotFoundException {
        //given
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);

        List<String> fields = Arrays.asList("id", "br");
        String table = "tt";

        when(connectionMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        String goodSQL = "CREATE TABLE IF NOT EXISTS public.tt( id VARCHAR(50)  NULL  ,br VARCHAR(50)  NULL  )";
        when(statement.execute(goodSQL)).thenReturn(true);
        boolean answer;
        //when
        answer = dbManager.createTableWithoutTypesFields(table, fields);
        //then
        Mockito.verify(statement).execute(goodSQL);
        assertTrue(answer);
    }

    @Test
    public void goodCreateTableWithoutTypeFieldsWith3Columns() throws SQLException, ClassNotFoundException {
        //given
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);

        List<String> fields = Arrays.asList("id", "br", "ks");
        String table = "tt";

        when(connectionMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        String goodSQL = "CREATE TABLE IF NOT EXISTS public.tt( id VARCHAR(50)  NULL  ,br VARCHAR(50)  NULL  ,ks VARCHAR(50)  NULL  )";
        when(statement.execute(goodSQL)).thenReturn(true);
        boolean answer;
        //when
        answer = dbManager.createTableWithoutTypesFields(table, fields);
        //then
        Mockito.verify(statement).execute(goodSQL);
        assertTrue(answer);
    }

    @Test
    public void goodSelectAllFromTable() throws SQLException, ClassNotFoundException {
        //given
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        ResultSetMetaData  metaData= Mockito.mock(ResultSetMetaData.class);

        List<List<String>> expectedTable = new LinkedList<>();
        expectedTable.add(Arrays.asList("ONE", "TWO"));
        expectedTable.add(Arrays.asList("one", "two"));
        expectedTable.add(Arrays.asList("one", "two"));
        String table = "tt";

        when(connectionMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        String goodSQL = "SELECT * from public.tt";
        when(statement.executeQuery(goodSQL)).thenReturn(resultSet);
        when(resultSet.getMetaData()).thenReturn(metaData);
        when(metaData.getColumnName(1)).thenReturn("ONE");
        when(metaData.getColumnName(2)).thenReturn("TWO");
        when(metaData.getColumnName(3)).thenThrow(new SQLException());
        when(resultSet.next()).thenReturn(true,true, false);
        when(resultSet.getString(1)).thenReturn("one");
        when(resultSet.getString(2)).thenReturn("two");
        when(resultSet.getString(3)).thenThrow(new SQLException());
        //when
        List<List<String>> answerTable = dbManager.selectAllFromTable(table);
        //then
        Mockito.verify(statement).executeQuery(goodSQL);
        assertEquals(expectedTable, answerTable);
    }

    @Test
    public void goodCloseConnection() throws SQLException, ClassNotFoundException {
        //given
        //when
         dbManager.closeConnection();
        //then
        Mockito.verify(connectionMock).closeConnection();
    }

    @Test
    public void goodClearTable() throws SQLException, ClassNotFoundException {
        //given
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);

        when(connectionMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        String goodSql = "TRUNCATE TABLE  public.tt";
        String tableName = "tt";
        boolean ansewer = false;
        //when
         ansewer = dbManager.clearTable(tableName);
        //then
        Mockito.verify(statement).execute(goodSql);
        assertTrue(ansewer);
    }

    @Test
    public void goodDeleteRowFromTable_withStringValue() throws SQLException, ClassNotFoundException {
        //given
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);

        when(connectionMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        String goodSql = "DELETE FROM public.tt  WHERE id= 'kkk' ;";
        String tableName = "tt";
        String field = "id";
        String value = "kkk";
        //when
        dbManager.deleteRowFromTable(tableName, field, value);
        //then
        Mockito.verify(statement).execute(goodSql);
    }

    @Test
    public void goodDeleteRowFromTable_withIntValue() throws SQLException, ClassNotFoundException {
        //given
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);

        when(connectionMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        String goodSql = "DELETE FROM public.tt  WHERE id= 10 ;";
        String tableName = "tt";
        String field = "id";
        String value = "10";
        //when
        dbManager.deleteRowFromTable(tableName, field, value);
        //then
        Mockito.verify(statement).execute(goodSql);
    }

    @Test
    public void goodInsertRow() throws SQLException, ClassNotFoundException {
        //given
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);
        when(connectionMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);

        Map<String, String> rows = new HashMap<>();
        rows.put("id", "1");
        rows.put("name", "vasya");
        String tableName = "tt";
        String goodSql = "INSERT INTO public.tt(name, id ) VALUES ('vasya', 1 )";
        //when
        dbManager.insertRow(tableName, rows);
        //then
        Mockito.verify(statement).execute(goodSql);
    }
}
