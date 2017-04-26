package ua.com.juja.sergiishcherbakov.sqlcmd.model.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by StrannikFujitsu on 21.04.2017.
 */
public interface ConnectionController {

    boolean setParameters(String databaseName, String login, String password) throws SQLException, ClassNotFoundException;

    boolean Connect();

    Connection getConnection()
            throws SQLException, ClassNotFoundException;
}
