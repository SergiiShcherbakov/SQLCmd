package ua.com.juja.sergiishcherbakov.sqlcmd;

import ua.com.juja.sergiishcherbakov.sqlcmd.controller.StartController;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.JDBCPostgresSQLDatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.ConsoleViewer;

/**
 * Created by StrannikFujitsu on 19.04.2017.
 * This class starts program SQLCmd
 */
public class Main {
    public static void main(String[] args) throws Exception {

        ConsoleViewer viewer = new ConsoleViewer();
        JDBCPostgresSQLDatabaseManager databaseManager = new JDBCPostgresSQLDatabaseManager();

        new StartController(viewer, databaseManager)
                .start();
    }
}