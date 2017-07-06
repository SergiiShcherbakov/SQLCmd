package ua.com.juja.sergiishcherbakov.sqlcmd.controller;

import ua.com.juja.sergiishcherbakov.sqlcmd.controller.command.Command;
import ua.com.juja.sergiishcherbakov.sqlcmd.controller.command.ConnectCommand;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;


/**
 * Created by Sergii Shcherbakov on 23.04.2017.
 * The class provide user registration and starts main menu
 */
public class StartController {
    private Viewer viewer;
    private DatabaseManager databaseManager;

    public StartController(Viewer viewer , DatabaseManager databaseManager ) {
        this.viewer = viewer;
        this.databaseManager = databaseManager;
    }

    public void start(){
        printWelcome();
        viewer.write("please enter your data in format:\"databaseName|userName|password\": ");
        ConnectCommand connectCommand = new ConnectCommand();
        String command =  viewer.read();
        while(!connectCommand.connectToDB(viewer, databaseManager, command) ){
            viewer.write("please enter your data in format:\"databaseName|userName|password\": ");
            command =  viewer.read();
        }
        new MainMenu(databaseManager, viewer, Command.class).start();
        return;
    }

    private void printWelcome() {
        viewer.write("You started program SQLCmd from Sergii Shcherbakov");
        viewer.write("the program can to connect to your local database");
    }
}
