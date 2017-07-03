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

    boolean createNewTable(String tableName, Field[] fields);

    boolean createTableWithoutTypesFields(String tableName, List<String> addColumn);

    boolean deleteTable(String tableName);

    List<String> getTablesNames();

    void closeConnection();

   List<List<String>> selectAllFromTable(String parameter);

    boolean clearTable(String parameter);

    String insertRow(String tableName, Map<String, String> addRowToTable);

    void deleteRowFromTable(String tableName, String fieldName, String value);

    boolean updateTable(String tableName, Pair<String, String> whereColumnValue, Map<String, String> changeColumnValue);

}
