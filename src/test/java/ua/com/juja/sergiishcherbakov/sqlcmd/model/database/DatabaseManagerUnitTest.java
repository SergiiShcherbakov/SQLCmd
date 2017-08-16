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
    private
    ConnectionController connectionControllerMock = Mockito.mock(ConnectionController.class);

    @InjectMocks
    private
    JDBCPostgresSQLDatabaseManager dbManager = new JDBCPostgresSQLDatabaseManager();

    @Test
    public void goodUpdateCommandByIdwWith2Parameters() throws SQLException, ClassNotFoundException {
        //given
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);

        when(connectionControllerMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        String goodSQL = "update public.user set password='tttt', login='petya'  where id=1 ;";
        when(statement.execute(goodSQL)).thenReturn(true);

        String table = "user";
        Pair<String, String> idColumn = new Pair<>("id", "1");
        Map<String, String> changes = new HashMap<>();
        changes.put("login", "petya");
        changes.put("password", "tttt");
        //when
        boolean answer = dbManager.updateTable(table, idColumn, changes);
        //then
        Mockito.verify(statement).execute(goodSQL);
        assertTrue(answer);
    }

    @Test
    public void goodUpdateCommandByIdwWith1Parameters() throws SQLException, ClassNotFoundException {
        //given
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);

        when(connectionControllerMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        String goodSQL = "update public.temppp set login='stiven'  where id=7 ;";
        when(statement.execute(goodSQL)).thenReturn(true);

        String table = "temppp";
        Pair<String, String> idColumn = new Pair<>("id", "7");
        Map<String, String> changes = new HashMap<>();
        changes.put("login", "stiven");
        boolean answer;
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

        when(connectionControllerMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        String goodSQL = "create table if not exists public.tt( id VARCHAR(50)  NULL  ,br VARCHAR(50)  NULL  )";
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

        when(connectionControllerMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        String goodSQL = "create table if not exists public.tt( id VARCHAR(50)  NULL  ,br VARCHAR(50)  NULL  ,ks VARCHAR(50)  NULL  )";
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

        when(connectionControllerMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        String goodSQL = "select * from public.tt";
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
        Mockito.verify(connectionControllerMock).closeConnection();
    }

    @Test
    public void goodClearTable() throws SQLException, ClassNotFoundException {
        //given
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);

        when(connectionControllerMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        String goodSql = "truncate table  public.tt";
        String tableName = "tt";
        boolean answer;
        //when
         answer = dbManager.clearTable(tableName);
        //then
        Mockito.verify(statement).execute(goodSql);
        assertTrue(answer);
    }

    @Test
    public void goodDeleteRowFromTable_withStringValue() throws SQLException, ClassNotFoundException {
        //given
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);

        when(connectionControllerMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        String goodSql = "delete from public.tt  where id= 'kkk' ;";
        String tableName = "tt";
        String field = "id";
        String value = "kkk";
        //when
        dbManager.deleteRow(tableName, field, value);
        //then
        Mockito.verify(statement).execute(goodSql);
    }

    @Test
    public void goodDeleteRowFromTable_withIntValue() throws SQLException, ClassNotFoundException {
        //given
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);

        when(connectionControllerMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        String goodSql = "delete from public.tt  where id= 10 ;";
        String tableName = "tt";
        String field = "id";
        String value = "10";
        //when
        dbManager.deleteRow(tableName, field, value);
        //then
        Mockito.verify(statement).execute(goodSql);
    }

    @Test
    public void goodInsertRow() throws SQLException, ClassNotFoundException {
        //given
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);
        when(connectionControllerMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);

        Map<String, String> rows = new HashMap<>();
        rows.put("id", "1");
        rows.put("name", "vasya");
        String tableName = "tt";
        String goodSql = "insert into public.tt(name, id ) values ('vasya', 1 )";
        //when
        dbManager.insertRow(tableName, rows);
        //then
        Mockito.verify(statement).execute(goodSql);
    }
}
