package ua.com.juja.sergiishcherbakov.sqlcmd.controller;

import ua.com.juja.sergiishcherbakov.sqlcmd.controller.command.Command;
import ua.com.juja.sergiishcherbakov.sqlcmd.controller.command.MenuCommandFactory;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.util.List;

/**
 * Created by Sergii Shcherbakov on 23.04.2017.
 */
public class MainMenu {

    private DatabaseManager databaseManager;
    private Viewer viewer;
    private List <Command> menuCommandList;

    public MainMenu(DatabaseManager databaseManager, Viewer viewer, Class classs) {
        this.databaseManager = databaseManager;
        this.viewer = viewer;
        this.menuCommandList = new MenuCommandFactory().getMenuCommand(classs);
    }

    void start()  {
        mainMenuWelcome();
        while(!isThisCommandLast()) {
            // do nothing
        }
        return ;
    }

    private boolean isThisCommandLast()  {
        viewer.write("Enter your command or type help to get help:");
        String inputCommand  = viewer.read();

        boolean commandWasProcessed = false;
        boolean isTheLastCommand = false;
        for (Command command: menuCommandList) {
            if(command.canProcess(inputCommand)){
                isTheLastCommand = command.processAndExit(viewer, databaseManager, inputCommand);
                commandWasProcessed = true;
                break;
            }
        }
        if (!commandWasProcessed) {
            viewer.write(inputCommand + " does not supported.");
            return false;
        }
        return isTheLastCommand;
    }

    private void mainMenuWelcome() {
        viewer.write("Main menu:");
    }
}
