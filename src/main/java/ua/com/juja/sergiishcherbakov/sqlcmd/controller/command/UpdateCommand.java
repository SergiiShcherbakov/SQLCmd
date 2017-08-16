package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import javafx.util.Pair;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergii Shcherbakov on 06.05.2017.
 */
class UpdateCommand extends CommandSkeleton implements Command {

    private static final int TABLE_NAME = 1;
    private static final int COLUMN = 2;
    private static final int VALUE = 3;

    public UpdateCommand() {
        super("update",
                "\tupdate table where column 1 = value1 " + System.lineSeparator() +
                        "\t\t\twith values 2 ... value n in column 2 - column n specified by user" + System.lineSeparator() +
                        "\t\t\tformat the command:" + System.lineSeparator() +
                        "\t\t\tupdate|tableName|column1|value1|column2|value2|...|columnN|valueN");
    }

    @Override
    String[] prepareParameters(String inputCommand) {
        return CorrectParameterChecker.getGetOddParameters(this.getName(), inputCommand, 6);
    }

    @Override
    Object prepareDataToViewer(String[] parameters) {
        Map updateColumnValues = new HashMap();
        for (int i = 4; i <parameters.length ; i+= 2) {
            updateColumnValues.put(parameters[i], parameters[i+ 1]);
        }
        databaseManager.updateTable(parameters[TABLE_NAME],
                new Pair<>(parameters[COLUMN], parameters[VALUE]), updateColumnValues);
        viewer.write("in table \"" + parameters[TABLE_NAME]
                + "\" was updated row(s) with column \"" + parameters[2] +
                "\"=\"" + parameters[VALUE] + "\"" );
        return databaseManager.selectAllFromTable(parameters[TABLE_NAME]);
    }

    @Override
    protected void viewResult(Object result) {
        viewer.printTable((List<List <String>>) result);
    }
}
