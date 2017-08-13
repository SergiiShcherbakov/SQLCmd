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

    public static final int TABLE_NAME = 1;

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
        if( databaseManager.clearTable(parameters[TABLE_NAME])){
            return  String.format( "table %s was cleared",  parameters[TABLE_NAME]) ;
        }else{
            throw new RuntimeException(String.format( "Table %s was not cleared.", parameters[TABLE_NAME]) );
        }
    }
}
