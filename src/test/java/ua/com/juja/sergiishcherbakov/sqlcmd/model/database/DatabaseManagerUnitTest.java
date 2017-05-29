package ua.com.juja.sergiishcherbakov.sqlcmd.model.database;

import javafx.util.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

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
        DatabaseManager dbManager = new JDBCPostgresSQLDatabaseManager();
    @Test
    public void goodUpdateCommandByIdwWithTwoParameters() throws SQLException, ClassNotFoundException {
        //given
        String goodSQL = "UPDATE public.user SET password='tttt', login='petya' WHERE id=1 ;";
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);

        when(connectionMock.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
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
}
