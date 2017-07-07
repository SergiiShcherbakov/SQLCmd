package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.exeptions.IncorrectNumberOfParametersException;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.FirstTablePrinter;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sergii Shcherbakov on 06.05.2017.
 */
public class FindCommand extends CommandSkeleton implements Command {

    public FindCommand() {
        super("find",
                "\tfind and print tables specified by user"+ System.lineSeparator() +
                        "\t\tformat the command:" + System.lineSeparator() +
                        "\t\t find|\"table name\"");
    }

    @Override
    String[] prepareParameters(String inputCommand) {
        return CorrectParameterChecker.getCorrectNumberOfParameters(this.getName(), inputCommand, 2);
    }

    @Override
    Object prepareDataToViewer(String[] parameters) {
        return  databaseManager.selectAllFromTable(parameters[1]);
    }

    @Override
    protected void viewResult(Object result) {
        viewer.printTable( (List<List<String>>) result);
    }
}
