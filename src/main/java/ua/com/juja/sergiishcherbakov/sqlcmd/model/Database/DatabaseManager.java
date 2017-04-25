package ua.com.juja.sergiishcherbakov.sqlcmd.model.Database;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.Field;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by StrannikFujitsu on 23.04.2017.
 */
public interface DatabaseManager {
    boolean setConnection(String databaseName, String login, String password) throws SQLException, ClassNotFoundException;

    boolean createNewTable(String tableName, Field[] fields) throws SQLException, ClassNotFoundException;

    boolean deleteTable(String tableName) throws SQLException, ClassNotFoundException;

    List<String> getTablesNames() throws SQLException, ClassNotFoundException;

}
