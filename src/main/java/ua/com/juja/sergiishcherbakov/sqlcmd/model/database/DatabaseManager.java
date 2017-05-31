package ua.com.juja.sergiishcherbakov.sqlcmd.model.database;

import javafx.util.Pair;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.Field;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergii Shcherbakov on 23.04.2017.
 */
public interface DatabaseManager {
    boolean setConnection(String databaseName, String login, String password)
            throws SQLException, ClassNotFoundException;

    boolean createNewTable(String tableName, Field[] fields)
            throws SQLException, ClassNotFoundException;

    boolean deleteTable(String tableName)
            throws SQLException, ClassNotFoundException;

    List<String> getTablesNames()
            throws SQLException, ClassNotFoundException;

    void closeConnection();

   List<List<String>> selectAllFromTable(String parameter)
           throws SQLException, ClassNotFoundException;

    boolean clearTable(String parameter) throws SQLException, ClassNotFoundException;

    String insertRow(String tableName, Map<String, String> addRowToTable) throws SQLException, ClassNotFoundException;

    void deleteRowFromTable(String tableName, String fieldName, String value) throws SQLException, ClassNotFoundException;

    boolean updateTable(String tableName, Pair<String, String> whereColumnValue, Map<String, String> changeColumnValue)
            throws SQLException, ClassNotFoundException;

    boolean createTableWithoutTypesFields(String tableName, List<String> addColumn) throws SQLException, ClassNotFoundException;
}
