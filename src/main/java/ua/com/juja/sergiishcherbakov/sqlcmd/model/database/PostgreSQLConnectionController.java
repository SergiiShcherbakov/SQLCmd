package ua.com.juja.sergiishcherbakov.sqlcmd.model.database;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by StrannikFujitsu on 21.04.2017.
 */
public class PostgreSQLConnectionController implements ConnectionController {
    private  String databaseName;
    private String login;
    private String password;

    @Resource
    private  Connection connection; // todo add connections pull

    @Override
    public void setParameters(String databaseName, String login, String password) throws SQLException, ClassNotFoundException {
        this.databaseName = databaseName;
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Connection getConnection( )
            throws SQLException, ClassNotFoundException {
            Class.forName("org.postgresql.Driver");
        if (connection == null) {
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/"+databaseName,
                        login,
                        password);
        }
         return connection;
    }

    // todo realize connection pull and after using connection return it to connection pul
    @Override
    public void closeConnection() {
        try {
            if(connection == null) {
                return;
            }
            connection.close();
            connection = null;
        } catch (SQLException e) {
           throw new RuntimeException("Connection was not close");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if(connection != null)
            connection.close();
    }
}
