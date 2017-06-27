package ua.com.juja.sergiishcherbakov.sqlcmd.controller;

import ua.com.juja.sergiishcherbakov.sqlcmd.controller.command.Command;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sergii Shcherbakov on 23.04.2017.
 */
public class MainMenu {

    private DatabaseManager databaseManager;
    private Viewer viewer;
    private List <Command> menuCommandList;

    public MainMenu(DatabaseManager databaseManager, Viewer viewer, List<Command> menuCommandList) {
        this.databaseManager = databaseManager;
        this.viewer = viewer;
        this.menuCommandList = menuCommandList;
    }

    void start() throws Exception {
        welcome();
        while(!isThisCommandLast()) { }
        return ;
    }

    private boolean isThisCommandLast() throws SQLException, ClassNotFoundException {
        boolean isTheLastCommand = false;
        boolean commandWasProcessed = false;
        String inputCommand = "";
        viewer.write("Enter your command or type help to get help:");
        inputCommand = viewer.read();

        for (Command command: menuCommandList) {
            if(command.canProcess(inputCommand)){
                isTheLastCommand = command.processAndExit(viewer, databaseManager, inputCommand);
                commandWasProcessed = true;
                continue;
            }
        }
        if (!commandWasProcessed) {
            viewer.write(inputCommand + " does not supported.");
            return false;
        }
        return isTheLastCommand;
    }

    private void welcome() {
        viewer.write("Main menu:");
    }
}
