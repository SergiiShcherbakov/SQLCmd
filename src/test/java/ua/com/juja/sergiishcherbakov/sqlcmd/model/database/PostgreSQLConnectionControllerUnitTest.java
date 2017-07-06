package ua.com.juja.sergiishcherbakov.sqlcmd.model.database;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;

/**
 * Created by Sergii Shcherbakov on 14.06.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class PostgreSQLConnectionControllerUnitTest {
    Connection connection = Mockito.mock(Connection.class);

    @InjectMocks
    ConnectionController connectionController =  new PostgreSQLConnectionController();

    @Test
    public void isWorkCloseConnection_Test() throws SQLException, ClassNotFoundException {
        //given
        //when
        connectionController.closeConnection();
        //then
        Mockito.verify(connection).close();
        assertFalse(connectionController.isConnected());

    }

    @Test(expected = RuntimeException.class)
    public void isWorkCloseConnectionWithExceptionTest() throws SQLException {
        //given
        Mockito.doThrow(new SQLException()).when(connection).close();
        //when
        connectionController.closeConnection();
        //then
    }
}
