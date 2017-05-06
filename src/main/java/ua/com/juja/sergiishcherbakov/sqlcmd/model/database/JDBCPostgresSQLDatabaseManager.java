package ua.com.juja.sergiishcherbakov.sqlcmd.model.database;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.Field;
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

        return connectionController.setParameters(databaseName,  login,  password);
    }

    @Override
    public boolean createNewTable(String tableName, Field[] fields)
            throws SQLException, ClassNotFoundException {

        Connection connection = connectionController.getConnection();
        if(connection != null) {
            try (Statement statement = connection.createStatement()) {

                String sql = "CREATE TABLE IF NOT EXISTS public." + tableName + "( ";
                for (int i = 0; i < fields.length; i++) {
                    sql += fields[i].getSqlField();
                    if (i != fields.length - 1) sql += " ,";
                    else sql += " )";
                }
                statement.executeUpdate(sql);
            }
        } else {
            throw new RuntimeException("DatabaseManager.createNewTable is fall! It haven`t connection");
        }
        return true;
    }

    @Override
    public boolean deleteTable(String tableName)
            throws SQLException, ClassNotFoundException {

        Connection connection = connectionController.getConnection();
        if( connection != null) {
            try (Statement statement = connection.createStatement()) {
                String sql = "Drop TABLE IF EXISTS public." + tableName;
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
                 ResultSet rs =
                         statement.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='public'")){

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
}
