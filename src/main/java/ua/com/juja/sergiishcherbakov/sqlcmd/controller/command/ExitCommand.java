package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLException;

/**
 * Created by Sergii Shcherbakov on 01.05.2017.
 */
public class ExitCommand extends CommandSkeleton implements Command {

    public ExitCommand() {
        super("exit",
         "\texit from the program");
    }

    @Override
    public boolean processAndExit(Viewer viewer, DatabaseManager databaseManager, String inputCommand) {

        try {
            if ( !canProcess( inputCommand)) {
                throw new IllegalArgumentException("parameter \"exit\" are expected but " +
                        inputCommand +
                        " is entered" );
            }
            viewer.write("Good by. See you soon.");
            databaseManager.closeConnection();
            return true;
        } catch ( IllegalArgumentException e ) {
            viewer.write(e.getMessage());
            viewer.write("please, try again");
        }
        return false;
    }

    @Override
    public boolean canProcess(String command) {
        return command.toLowerCase().equals(name);
    }
}
