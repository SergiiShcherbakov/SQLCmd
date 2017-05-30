package ua.com.juja.sergiishcherbakov.sqlcmd.model.database;

import javafx.util.Pair;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.Field;

import javax.annotation.Resource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergii Shcherbakov on 21.04.2017.
 */
public class JDBCPostgresSQLDatabaseManager implements DatabaseManager {
    @Resource
    private ConnectionController connectionController;

    public JDBCPostgresSQLDatabaseManager() {
        this.connectionController =  new PostgreSQLConnectionController( );
    }

    @Override
    public boolean setConnection(String databaseName, String login, String password)
            throws SQLException, ClassNotFoundException {
        connectionController.setParameters(databaseName,  login,  password);
        connectionController.getConnection();
        return connectionController.isConnected();
    }

    @Override
    public boolean createNewTable(String tableName, Field[] fields) throws SQLException, ClassNotFoundException {
        Connection connection = connectionController.getConnection();
        if(connection != null) {
            try (Statement statement = connection.createStatement()) {
                StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS public." + tableName + "( ");
                for (int i = 0; i < fields.length; i++) {
                    sql.append(fields[i].getSqlField());
                    if (i != fields.length - 1) sql.append(" ,");
                    else sql.append(" )");
                }
                statement.executeUpdate(sql.toString());
            }
        } else {
            throw new RuntimeException("DatabaseManager.createNewTable is fall! It haven`t connection");
        }
        return true;
    }

    @Override
    public boolean deleteTable(String tableName) throws SQLException, ClassNotFoundException {
        Connection connection = connectionController.getConnection();
        if( connection != null) {
            try (Statement statement = connection.createStatement()) {
                String sql = "Drop TABLE public." + tableName;
                statement.executeUpdate(sql);
            }
        }else{
            throw new RuntimeException("DatabaseManager.deleteTable is fall! It haven`t connection");
        }
        return true;
    }

    @Override
    public List<String> getTablesNames() throws SQLException, ClassNotFoundException {
        Connection connection = connectionController.getConnection();
        if(connection != null) {
            LinkedList<String> result;
            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery("SELECT table_name " +
                         "FROM information_schema.tables WHERE table_schema='public'")){
                    result = new LinkedList<>();
                    while (rs.next() ){
                        result.add( rs.getString(1));
                    }
            }
            return result;
        }else{
            throw new RuntimeException("DatabaseManager.getTablesNames is fall! It haven`t connection");
        }
    }

    @Override
    public void closeConnection() {
        connectionController.closeConnection();
    }

    @Override
    public List<List<String>> selectAllFromTable(String tableName) throws SQLException, ClassNotFoundException {
        List<List<String>> result = new LinkedList<>();
        Connection connection = connectionController.getConnection();
        if(connection != null) {
            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery("SELECT * from public." + tableName)
            ){
                result.add(getTitles(rs));
                while (rs.next() ){
                    result.add(getDataFromRow(rs));
                }
            }
            return result;
        }else{
            throw new RuntimeException("DatabaseManager.getTablesNames is fall! It haven`t connection");
        }
    }

    @Override
    public boolean clearTable(String tableName) throws SQLException, ClassNotFoundException {
        Connection connection = connectionController.getConnection();
        if( connection != null) {
            try (Statement statement = connection.createStatement()) {
                String sql = "TRUNCATE TABLE  public." + tableName;
                //todo check will it work with execute()
                statement.executeUpdate(sql);
            }
        }else{
            throw new RuntimeException("DatabaseManager.deleteTable is fall! It haven`t connection");
        }
        return true;
    }

    @Override
    public String insertRow(String tableName, Map<String, String> addRowToTable) throws SQLException, ClassNotFoundException {
        String result = "Ok";
        Connection connection = connectionController.getConnection();
        if( connection != null) {
            StringBuilder columns = new StringBuilder();
            StringBuilder values = new StringBuilder();
            for (Map.Entry<String, String> row : addRowToTable.entrySet()) {
                columns.append(row.getKey() + ", ");
                addDataByValues(values, row.getValue() );
            }
            columns.deleteCharAt(columns.length()-2);
            values.deleteCharAt(values.length()-2);
            String sql =  "INSERT INTO public."+ tableName + "(" +
                      columns + ") VALUES (" + values + ")";
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
        // todo add insert answer about default values
            }
        }else{
            throw new RuntimeException("DatabaseManager.deleteTable is fall! It haven`t connection");
        }
        return result;
    }

    @Override
    public void deleteRowFromTable(String tableName, String fieldName, String value) throws SQLException, ClassNotFoundException {
        Connection connection = connectionController.getConnection();
        if( connection != null) {
            StringBuilder val = new StringBuilder();
            addDataByValues(val, value);
            val.deleteCharAt(val.length()-2);
            String sql =  "DELETE FROM public." + tableName +
                    "  WHERE " + fieldName + "= " + val + ";";
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
            }
        }else{
            throw new RuntimeException("DatabaseManager.deleteTable is fall! It haven`t connection");
        }
    }

    @Override
    public boolean updateTable(String tableName, Pair<String, String> whereColumnValue, Map<String, String> changeColumnValue) throws SQLException, ClassNotFoundException {
        Connection connection = connectionController.getConnection();
        if( connection != null) {
            StringBuilder WhereValue = new StringBuilder();
            addDataByValues(WhereValue, whereColumnValue.getValue());
            WhereValue.deleteCharAt(WhereValue.length()-2);
            StringBuilder changes = new StringBuilder();
            for (Map.Entry<String, String> change : changeColumnValue.entrySet()) {
                changes.append(change.getKey())
                        .append('=');
                        addDataByValues(changes, change.getValue());
            }
            changes.deleteCharAt(changes.length()-2);
            String sql =  "UPDATE public." + tableName +
                    " SET " + changes +
                    "WHERE " + whereColumnValue.getKey() + "=" + WhereValue + ";";
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
            }
        }else{
            throw new RuntimeException("DatabaseManager.deleteTable is fall! It haven`t connection");
        }
        return true;
    }

    @Override
    public boolean createTableWithoutTypesColumn(String tableName, List<String> addColumn) throws SQLException, ClassNotFoundException {
        throw  new RuntimeException("method create table is not realize in class JDBCPostgresSQLDatabaseManager");
    }

    private void addDataByValues(StringBuilder values, String value) {
        if (isNumeric(value)) {
            values.append(value + ", ");
        } else {
            values.append("'" + value + "', ");
        }
    }

    private List<String> getTitles(ResultSet rs) {

        LinkedList<String> result = new LinkedList<>();
        ResultSetMetaData metadata = null;
        try {
            metadata = rs.getMetaData();
            int i=1;
            while (true){
                String name = metadata.getColumnName(i++);
                result.add(name);
            }
        } catch (SQLException e) {
            // do nothing
        }
        return result;
    }

    private List<String> getDataFromRow(ResultSet rs) {
        List<String> result = new LinkedList<>();
        try {
            int i=1;
            while (true){
                String data = rs.getString(i++);
                result.add(data);
            }
        } catch (SQLException e) {
            // do nothing
        }
        return result;
    }

    public  boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
