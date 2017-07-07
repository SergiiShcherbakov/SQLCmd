package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.exeptions.IncorrectNumberOfParametersException;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

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
    String[] prepareParameters(String inputCommand) {
        CorrectParameterChecker.getCorrectParameter(getName(), inputCommand);
        databaseManager.closeConnection();
        return new String[]{ this.getName()};
    }

    @Override
    Object prepareDataToViewer(String[] parameters) {
        return "Good by. See you soon.";
    }

    @Override
    public boolean canProcess(String command) {
        return canProcessWithoutParameters(command);
    }
}
