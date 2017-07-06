package ua.com.juja.sergiishcherbakov.sqlcmd;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.JDBCPostgresSQLDatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.PostgreSQLConnectionController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Sergii Shcherbakov on 06.07.2017.
 */
public class DBSetupForTests {

    public static final String REAL_DB = "SQLCmd";
    public static final String DB_LOGIN = "postgres";
    public static final String DB_PASSWORD = "postgres";

    public static final String TEST_DB = "testdbforsqlcmd";

    public static void createTestDatabase() throws SQLException, ClassNotFoundException {
        JDBCPostgresSQLDatabaseManager dbm = new JDBCPostgresSQLDatabaseManager();
        dbm.setConnection(REAL_DB, DB_LOGIN, DB_PASSWORD);
        dbm.executeQuery("CREATE DATABASE " + TEST_DB);
        dbm.closeConnection();
    }

    public static void  deleteTestDatabase() throws SQLException, ClassNotFoundException {
        PostgreSQLConnectionController connectionController = new PostgreSQLConnectionController();
        connectionController.setParameters(REAL_DB, DB_LOGIN, DB_PASSWORD);
        Connection connection = connectionController.getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeQuery("SELECT  pg_terminate_backend (pg_stat_activity.pid) " +
                "FROM pg_stat_activity WHERE pg_stat_activity.datname = '" + TEST_DB + "';" );
        stmt.execute("DROP DATABASE if exists " + TEST_DB + " ;");
        stmt.close();
        connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        createTestDatabase();
        deleteTestDatabase();
    }
}
