package ua.com.juja.sergiishcherbakov.sqlcmd.controller;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

/**
 * Created by StrannikFujitsu on 23.04.2017.
 */
public class MainMenu {

    private DatabaseManager databaseManager;
    private Viewer viewer;

    public MainMenu(DatabaseManager databaseManager, Viewer viewer) {

        this.databaseManager = databaseManager;
        this.viewer = viewer;
    }

    boolean start() throws Exception {
        viewer.write("Connection successful!");
        viewer.write("Main menu:");
        return true;
//     throw  new Exception("Method start do not realized!!");
    }
}
