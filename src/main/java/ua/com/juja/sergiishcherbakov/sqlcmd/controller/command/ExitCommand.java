package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

/**
 * Created by Sergii Shcherbakov on 01.05.2017.
 */
public class ExitCommand extends CommandSkeleton {

    public ExitCommand() {
        super("exit",
         "\texit from the program");
    }

    @Override
    public boolean processAndExit(Viewer viewer, DatabaseManager databaseManager, String inputCommand) {
        viewer.write("Good by. See you soon.");
        databaseManager.closeConnection();
        return true;
    }
}
