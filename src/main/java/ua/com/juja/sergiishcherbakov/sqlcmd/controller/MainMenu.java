package ua.com.juja.sergiishcherbakov.sqlcmd.controller;

import ua.com.juja.sergiishcherbakov.sqlcmd.controller.comand.MenuCommand;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.util.Map;

/**
 * Created by StrannikFujitsu on 23.04.2017.
 */
public class MainMenu {

    private DatabaseManager databaseManager;
    private Viewer viewer;

    private Map<String, MenuCommand> menuComandMap;


    public MainMenu(DatabaseManager databaseManager, Viewer viewer, Map<String, MenuCommand> menuComandMap) {

        this.databaseManager = databaseManager;
        this.viewer = viewer;

        this.menuComandMap = menuComandMap;
    }

    boolean start() throws Exception {
        viewer.write("Connection successful!");
        viewer.write("Main menu:");
        boolean isExit = false;
        String inputCommand = "";
        MenuCommand menuCommand;

        while(!isExit) {
            viewer.write("Enter your command or type help to get help:");
            inputCommand = viewer.read("String");

            menuCommand = menuComandMap.get(inputCommand);
            if(menuCommand != null)  isExit = menuCommand.process();
        }
        return true;
    }

    private void printMenuComands() {
    }

}
