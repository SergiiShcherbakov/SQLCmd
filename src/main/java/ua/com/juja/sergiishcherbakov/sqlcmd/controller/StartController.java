package ua.com.juja.sergiishcherbakov.sqlcmd.controller;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.Database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.Database.JDBCPostgresSQLDatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.ConsoleViewer;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

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

        viewer.write("You started program SQLCmd from Sergii Shcserbakow\n" +
        "the program can to connect to your localhost database\n" +
        "please enter your data in format:\"databaseName|userName|password\": ");

        String[] data = viewer.read(" ").split("[|]");
        String databaseName = data[0] ;
        String login = data[1];
        String password = data[2];
        viewer.write("connect to database");

        if (databaseManager.setConnection(databaseName, login, password)) {
            new MainMenu(databaseManager, viewer).start();

        }

    }

    public static void main(String[] args) throws Exception {
        new StartController(new ConsoleViewer(),
                new JDBCPostgresSQLDatabaseManager())
                .start();
    }
}
