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
        super("exit",  "\texit from the program");
    }

    @Override
    String[] prepareParameters(String inputCommand) {
        CorrectParameterChecker.getCorrectParameter(getName(), inputCommand);
        databaseManager.closeConnection();
        return new String[]{ this.getName()};
    }

    @Override
    Object prepareDataToViewer(String[] parameters) {
        return "good by, see you soon.";
    }

    @Override
    public boolean canProcess(String command) {
        return canProcessWithoutParameters(command);
    }

    @Override
    boolean isLastCommand() {
        return true;
    }
}
