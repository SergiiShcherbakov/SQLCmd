package ua.com.juja.sergiishcherbakov.sqlcmd;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.JDBCPostgresSQLDatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.PostgreSQLConnectionController;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by Sergii Shcherbakov on 06.07.2017.
 */
public class DBSetupForTests {

    private static  String real_db;
    private  static String db_login;
    private static  String db_password ;

    static  {
        Properties property = new Properties();
        FileInputStream fis;

        try {
            fis = new FileInputStream("src/main/resources/config/sqlcmd.properties");
            property.load(fis);

            real_db = property.getProperty("REAL_DB");
            db_login = property.getProperty("DB_LOGIN");
            db_password = property.getProperty("DB_PASSWORD");

        } catch (IOException e) {
            System.err.println("fail: properties file is absent!");
        }

    }

    public static final String TEST_DB = "testdbforsqlcmd87097235outw";

    public static String getDBName() {
        return real_db;
    }

    public static String getDBLogin() {
        return db_login;
    }

    public static String getDBPassword() {
        return db_password;
    }

    public static void createTestDatabase() throws SQLException, ClassNotFoundException {
        JDBCPostgresSQLDatabaseManager dbManager = new JDBCPostgresSQLDatabaseManager();
        dbManager.setConnection(real_db, db_login, db_password);
        dbManager.executeQuery("CREATE DATABASE " + TEST_DB);
        dbManager.closeConnection();
    }

    public static void  deleteTestDatabase() throws SQLException, ClassNotFoundException {
        PostgreSQLConnectionController connectionController = new PostgreSQLConnectionController();
        connectionController.setParameters(real_db, db_login, db_password);
        try(
                Connection connection = connectionController.getConnection();
                Statement stmt = connection.createStatement()
        ){
            stmt.executeQuery("SELECT  pg_terminate_backend (pg_stat_activity.pid) " +
                    "FROM pg_stat_activity WHERE pg_stat_activity.datname = '" + TEST_DB + "';" );
            stmt.execute("DROP DATABASE if exists " + TEST_DB + " ;");
        }
    }
}
