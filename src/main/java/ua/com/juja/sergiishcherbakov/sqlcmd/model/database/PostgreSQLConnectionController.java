package ua.com.juja.sergiishcherbakov.sqlcmd.model.database;

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

    private  Connection connection; // todo add connections pull


    @Override
    public boolean setParameters(String databaseName, String login, String password) throws SQLException, ClassNotFoundException {
        this.databaseName = databaseName;
        this.login = login;
        this.password = password;
        getConnection();
        return Connect();
    }

    @Override
    public boolean Connect() {
        return connection != null;
    }

    public Connection getConnection( )
            throws SQLException, ClassNotFoundException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw e;
        }

        if (connection == null) {


                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/"+databaseName,
                        login,
                        password);


        }
         return connection;
    }
}
