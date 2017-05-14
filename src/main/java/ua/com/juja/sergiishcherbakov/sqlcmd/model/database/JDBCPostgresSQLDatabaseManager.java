package ua.com.juja.sergiishcherbakov.sqlcmd.model.database;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.Field;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.ConsoleViewer;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.FirstTablePrinter;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.TablePrinter;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sergii Shcherbakov on 21.04.2017.
 */
public class JDBCPostgresSQLDatabaseManager implements DatabaseManager {

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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseManager db = new JDBCPostgresSQLDatabaseManager();
        db.setConnection("SQLCmd" ,"postgres","z");
        List<List<String>> tab = db.selectAllFromTable("user");
        Viewer console = new ConsoleViewer();
        TablePrinter tp = new FirstTablePrinter(console);
        console.printTable(tab);
    }
}
