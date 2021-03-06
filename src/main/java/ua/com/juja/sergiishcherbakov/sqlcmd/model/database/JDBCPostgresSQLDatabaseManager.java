package ua.com.juja.sergiishcherbakov.sqlcmd.model.database;

import javafx.util.Pair;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.Field;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.FieldType;

import javax.annotation.Resource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergii Shcherbakov on 21.04.2017.
 */
public class JDBCPostgresSQLDatabaseManager implements DatabaseManager {
    private static final int MAX_STRING_SIZE = 50;
    @SuppressWarnings("CanBeFinal")
    @Resource
    private  ConnectionController connectionController;

    public JDBCPostgresSQLDatabaseManager() {
        this.connectionController =  new PostgreSQLConnectionController( );
    }

    @Override
    public boolean setConnection(String databaseName, String login, String password) {
        try {
            connectionController.setParameters(databaseName,  login,  password);
            connectionController.getConnection();
        } catch (SQLException | ClassNotFoundException | RuntimeException e) {
            throw  new RuntimeException(e.getMessage());
        }
        return connectionController.isConnected();
    }

    @Override
    public boolean createTable(String tableName, Field[] fields) {
        StringBuilder sql = new StringBuilder("create table if not exists public.").append( tableName).append("( ");
        for (int i = 0; i < fields.length; i++) {
            sql.append(fields[i].getSqlField());
            if (i != fields.length - 1) sql.append(" ,");
            else sql.append(" )");
        }
        executeQuery(sql.toString());
        return true;
    }

    @Override
    public boolean createTableWithoutTypesFields(String tableName, List<String> addColumn){
        Field[] fields = new Field[addColumn.size()] ;
        int index = 0;
        for (String columnName : addColumn) {
            fields[index++] = new Field(columnName, FieldType.VARCHAR, false,
                    true, false, MAX_STRING_SIZE);
        }
        return createTable(tableName, fields );
    }

    @Override
    public boolean deleteTable(String tableName)  {
        String sql = String.format( "drop table public.%s" , tableName);
        executeQuery(sql);
        return true;
    }

    @Override
    public List<String> getTablesNames() {
        LinkedList<String> result;
        String sql = "select table_name from information_schema.tables where table_schema='public'";
        Connection connection = getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)
        ){
            result = new LinkedList<>();
            while (rs.next() ){
                result.add( rs.getString(1));
            }
        } catch (SQLException e ){
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public void closeConnection() {
        connectionController.closeConnection();
    }

    @Override
    public List<List<String>> selectAllFromTable(String tableName){
        List<List<String>> result = new LinkedList<>();
        Connection connection = getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery( String.format("select * from public.%s", tableName))
        ){
            result.add(getTitles(rs));
            while (rs.next() ){
                result.add(getDataFromRow(rs));
            }
        } catch (SQLException e ){
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean clearTable(String tableName) {
        String sql = String.format( "truncate table  public.%s", tableName);
        executeQuery(sql);
        return true;
    }

    @Override
    public String insertRow(String tableName, Map<String, String> addRowToTable){
        String result = "Ok";
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (Map.Entry<String, String> row : addRowToTable.entrySet()) {
            columns.append(row.getKey()).append(", ");
            addDataByValues(values, row.getValue() );
        }
        columns.deleteCharAt(columns.length()-2);
        values.deleteCharAt(values.length()-2);
        String sql =  String.format( "insert into public.%s(%s) values (%s)", tableName , columns, values);
        executeQuery(sql);
        return result;
    }

    @Override
    public void deleteRow(String tableName, String fieldName, String value) {
        StringBuilder val = new StringBuilder();
        addDataByValues(val, value);
        val.deleteCharAt(val.length()-2);
        String sql = String.format( "delete from public.%s  where %s= %s;",tableName, fieldName, val);
        executeQuery(sql);
    }

    @Override
    public boolean updateTable(String tableName, Pair<String, String> whereColumnValue,
                               Map<String, String> changeColumnValue) {
        StringBuilder whereValue = new StringBuilder();
        addDataByValues(whereValue, whereColumnValue.getValue());
        whereValue.deleteCharAt(whereValue.length()-2);
        StringBuilder changes = new StringBuilder();
        for (Map.Entry<String, String> change : changeColumnValue.entrySet()) {
            changes.append(change.getKey())
                    .append('=');
            addDataByValues(changes, change.getValue());
        }
        changes.deleteCharAt(changes.length()-2);
        String sql =  String.format( "update public.%s set %s where %s=%s;", tableName, changes,
                whereColumnValue.getKey(),whereValue) ;
        executeQuery(sql);
        return true;
    }

    public void executeQuery(String sql)  {
        Connection connection = getConnection();
        try {
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
            }
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private Connection getConnection(){
        try {
            Connection connection = connectionController.getConnection();
            //noinspection ConstantConditions
            if(connection != null || !connection.isClosed()) {
                return connection;
            } else {
                throw new RuntimeException("DatabaseManager.createTable is fall! It haven`t connection");
            }
        } catch (SQLException | ClassNotFoundException  e) {
            throw  new RuntimeException(e.getMessage());
        }
    }

    private void addDataByValues(StringBuilder values, String value) {
        if (isNumeric(value)) {
            values.append(value).append(", ");
        } else {
            values.append("'").append(value).append("', ");
        }
    }

    private List<String> getTitles(ResultSet rs) {
        LinkedList<String> result = new LinkedList<>();
        ResultSetMetaData metadata;
        try {
            metadata = rs.getMetaData();
            int i=1;
            //noinspection InfiniteLoopStatement
            while (true){
                String name = metadata.getColumnName(i++);
                result.add(name);
            }
        } catch (SQLException e) {
            // do nothing
        }
        return result;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private List<String> getDataFromRow(ResultSet rs) {
        List<String> result = new LinkedList<>();
        try {
            int i=1;
            //noinspection InfiniteLoopStatement
            while (true){
                String data = rs.getString(i++);
                result.add(data);
            }
        } catch (SQLException e) {
            // do nothing
        }
        return result;
    }

    private  boolean isNumeric(String str){
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

}
