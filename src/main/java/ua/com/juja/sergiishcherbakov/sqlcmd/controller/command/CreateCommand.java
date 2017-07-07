package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.exeptions.IncorrectNumberOfParametersException;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sergii Shcherbakov on 30.05.2017.
 */
public class CreateCommand extends CommandSkeleton {

    public CreateCommand() {
        super("create",
                "\tcreate new table specified by user"+ System.lineSeparator() +
                        "\t\tformat the command:" + System.lineSeparator() +
                        "\t\tcreate|tableName|column1|column2|...|columnN");
    }

    @Override
    String[] prepareParameters(String inputCommand) {
        return CorrectParameterChecker.getCorrectNumberOfParametersOrMore(this.getName(), inputCommand, 3);
    }

    @Override
    Object prepareDataToViewer(String[] parameters) {
        StringBuilder columnsName = new StringBuilder();
        List<String> columns = new LinkedList<>();
        for (int i = 2; i <parameters.length ; i++) {
            columns.add(parameters[i]);
            columnsName.append(parameters[i]).append(", ");
        }
        if(databaseManager.createTableWithoutTypesFields(parameters[1], columns)){
            columnsName.deleteCharAt(columnsName.length()-1);
            columnsName.deleteCharAt(columnsName.length()-1);
            return  String.format("table with name \"%s\" and with column \"%s\" added to current database",
                    parameters[1], columnsName);
        } else {
            throw new RuntimeException(String.format("table with name \"%s\" and with column \"%s\" was not add to current database",
                    parameters[1], columnsName));
        }
    }
}
