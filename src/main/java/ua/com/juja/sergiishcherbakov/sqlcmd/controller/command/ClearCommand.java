package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.exeptions.IncorrectNumberOfParametersException;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sergii Shcherbakov on 06.05.2017.
 */
public class ClearCommand extends CommandSkeleton  {

    public ClearCommand() {
        super("clear",
                "\tclear table specified by user"+ System.lineSeparator() +
                        "\t\t\tformat the command:" + System.lineSeparator() +
                        "\t\t\tclear|tableName");
    }

    @Override
    String[] prepareParameters(String inputCommand) {
        return CorrectParameterChecker.getCorrectNumberOfParameters(this.getName(), inputCommand, 2);
    }

    @Override
    Object prepareDataToViewer(String[] parameters) {
        if( databaseManager.clearTable(parameters[1])){
            return  "table " + parameters[1] + " was cleared";
        }else{
            throw new RuntimeException("Table " + parameters[1] + " was not cleared.");
        }
    }
}
