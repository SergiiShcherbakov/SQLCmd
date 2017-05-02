package ua.com.juja.sergiishcherbakov.sqlcmd.controller;

import ua.com.juja.sergiishcherbakov.sqlcmd.controller.comand.Command;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;
import java.util.List;

/**
 * Created by StrannikFujitsu on 23.04.2017.
 */
public class MainMenu {

    private DatabaseManager databaseManager;
    private Viewer viewer;
    private List <Command> menuCommandList;


    public MainMenu(DatabaseManager databaseManager, Viewer viewer, List<Command> menuComandList) {
        this.databaseManager = databaseManager;
        this.viewer = viewer;
        this.menuCommandList = menuComandList;
    }

    boolean start() throws Exception {
        viewer.write("Connection successful!");
        viewer.write("Main menu:");
        boolean isExit = false;
        String inputCommand = "";
        boolean commandWasProcessed = false;
        while(!isExit) {
            viewer.write("Enter your command or type help to get help:");
            inputCommand = viewer.read("String");
            for (Command command: menuCommandList) {
                if(command.canProcess(inputCommand)){
                    isExit = command.process(viewer, databaseManager, inputCommand);
                    commandWasProcessed = true;
                    continue;
                }
            }
            if (!commandWasProcessed) {
                viewer.write(inputCommand + " does not supported.");
            }
            commandWasProcessed = false;
        }
        return true;
    }
}
