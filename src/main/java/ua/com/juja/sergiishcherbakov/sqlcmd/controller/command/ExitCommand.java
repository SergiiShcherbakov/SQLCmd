package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.exeptions.IncorrectNumberOfParametersException;
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
            CorrectParameterChecker.getCorrectParameter(getName(), inputCommand);
            viewer.write("Good by. See you soon.");
            databaseManager.closeConnection();
            return true;
        } catch ( IncorrectNumberOfParametersException  e) {
            viewer.write( e.getMessage());
        }
        return false;
    }

    @Override
    public boolean canProcess(String command) {
        return canProcessWithoutParameters(command);
    }
}
