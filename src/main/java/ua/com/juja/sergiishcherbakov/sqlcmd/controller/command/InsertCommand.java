package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergii Shcherbakov on 06.05.2017.
 */
public class InsertCommand extends CommandSkeleton implements Command {

    public static final int TABLE_NAME = 1;

    public InsertCommand() {
        super("insert",
    "\tinsert row into table specified by user"+ System.lineSeparator() +
            "\t\t\tformat the command:" + System.lineSeparator() +
            "\t\t\tinsert|tableName|column1|value1|column2|value2|...|columnN|ValueN");
    }

    @Override
    String[] prepareParameters(String inputCommand) {
        return CorrectParameterChecker.getGetOddParameters(this.getName(), inputCommand, 4);
    }

    @Override
    Object prepareDataToViewer(String[] parameters) {
        Map newRow = new HashMap();
        StringBuilder row = new StringBuilder();
        for (int i = 2; i <parameters.length ; i+=2) {
            newRow.put(parameters[i], parameters[i+ 1]);
            row.append(parameters[i] + "=" + parameters[i+ 1] + ", ");
        }
        databaseManager.insertRow(parameters[TABLE_NAME], newRow);
        row.deleteCharAt(row.length()- 1);
        row.deleteCharAt(row.length()- 1);
        return String.format( "row \"%s\" was added to table \"%s\"", row, parameters[TABLE_NAME] ) ;
    }
}
