package ua.com.juja.sergiishcherbakov.sqlcmd;



import ua.com.juja.sergiishcherbakov.sqlcmd.controller.StartController;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.JDBCPostgresSQLDatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.ConsoleViewer;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.*;

/**
 * Created by StrannikFujitsu on 19.04.2017.
 * This class starts program SQLCmd
 */
public class Main {
    public static void main(String[] args) throws Exception {

        new StartController(new ConsoleViewer(),
                new JDBCPostgresSQLDatabaseManager())
                .start();

    }
}
