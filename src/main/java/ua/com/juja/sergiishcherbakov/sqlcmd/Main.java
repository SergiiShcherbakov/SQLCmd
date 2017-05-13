package ua.com.juja.sergiishcherbakov.sqlcmd;

import ua.com.juja.sergiishcherbakov.sqlcmd.controller.StartController;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.JDBCPostgresSQLDatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.ConsoleViewer;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.FirstTablePrinter;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

/**
 * Created by Sergii Shcherbakov on 19.04.2017.
 * This class starts program SQLCmd
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Viewer viewer = new ConsoleViewer();
        viewer.setTablePrinter(new FirstTablePrinter(viewer));
        DatabaseManager databaseManager = new JDBCPostgresSQLDatabaseManager();

        new StartController(viewer, databaseManager)
                .start();
    }
}