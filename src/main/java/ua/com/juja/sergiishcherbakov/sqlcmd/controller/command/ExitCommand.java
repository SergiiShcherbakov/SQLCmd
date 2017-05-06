package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

/**
 * Created by Sergii Shcherbakov on 01.05.2017.
 */
public class ExitCommand implements Command {

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return getName() +  "\texit from the program";
    }

    @Override
    public boolean processAndExit(Viewer viewer, DatabaseManager databaseManager, String inputCommand) {
        viewer.write("Good by. See you soon.");
        databaseManager.closeConnection();
        return true;
    }

    @Override
    public boolean canProcess(String command) {
        String newCommand = new String(command);
        newCommand.toLowerCase();
        return newCommand.equals("exit");
    }
}
