package ua.com.juja.sergiishcherbakov.sqlcmd.model.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Sergii Shcherbakov on 21.04.2017.
 */
interface ConnectionController {

    void setParameters(String databaseName, String login, String password)
            throws SQLException, ClassNotFoundException;

    boolean isConnected();

    Connection getConnection()
            throws SQLException, ClassNotFoundException;

    void closeConnection();
}
