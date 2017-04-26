package ua.com.juja.sergiishcherbakov.sqlcmd.controller;

import org.postgresql.util.PSQLException;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.JDBCPostgresSQLDatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.ConsoleViewer;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLDataException;
import java.sql.SQLException;

/**
 * Created by StrannikFujitsu on 23.04.2017.
 */
public class StartController {
    Viewer viewer;
    DatabaseManager databaseManager;


    public StartController(Viewer viewer , DatabaseManager databaseManager ) {
        this.viewer = viewer;
        this.databaseManager = databaseManager;
    }

    public void start() throws Exception {
//SQLCmd|postgres|z
        viewer.write("You started program SQLCmd from Sergii Shcserbakow\n" +
                "the program can to connect to your localhost database\n");
        while(true){
            viewer.write("please enter your data in format:\"databaseName|userName|password\": ");

            String[] data = viewer.read(" ").split("[|]");
            try {
            if (data.length < 3) {
                 throw new SQLException("3 parameters are expected but "+ data.length +" is entered" + "please, try again");
            }
            String databaseName = data[0];
            String login = data[1];
            String password = data[2];

                databaseManager.setConnection(databaseName, login, password);
                viewer.write("connect to database");
                new MainMenu(databaseManager, viewer).start();
                break;

            } catch (SQLException e ) {
                viewer.write(e.getMessage());
                viewer.write("please, try again");
            }
        }
        return;

    }

    public static void main(String[] args) throws Exception {
        new StartController(new ConsoleViewer(),
                new JDBCPostgresSQLDatabaseManager())
                .start();
    }
}
