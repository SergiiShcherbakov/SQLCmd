package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.exeptions.IncorrectNumberOfParametersException;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;
import java.sql.SQLException;

/**
 * Created by Sergii Shcherbakov on 06.05.2017.
 */
public class DropCommand extends CommandSkeleton implements Command {

    public static final int TABLE_NAME = 1;

    public DropCommand() {
        super("drop",
                "\tremove tables specified by user"+ System.lineSeparator() +
                        "\t\t\tformat the command:" + System.lineSeparator() +
                        "\t\t\tdrop|tableName");
    }

    @Override
    String[] prepareParameters(String inputCommand) {
        return CorrectParameterChecker.getCorrectNumberOfParameters(this.getName(), inputCommand, 2);
    }

    @Override
    Object prepareDataToViewer(String[] parameters) {
        databaseManager.deleteTable(parameters[TABLE_NAME]);
        return  String.format( "%s was removed",  parameters[TABLE_NAME]);
    }
}
