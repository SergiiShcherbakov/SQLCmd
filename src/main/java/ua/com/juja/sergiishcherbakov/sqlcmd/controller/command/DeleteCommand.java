package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;

/**
 * Created by Sergii Shcherbakov on 06.05.2017.
 */
public class DeleteCommand extends CommandSkeleton implements Command {

    public static final int TABLE_NAME = 1;
    public static final int COLUMN = 2;
    public static final int VALUE = 3;

    public DeleteCommand() {
        super("delete",
                "\tdelete row from table by name and value specified by user " + System.lineSeparator() +
                        "\t\t\tformat the command:" + System.lineSeparator() +
                        "\t\t\tdelete|tableName|column|value");
    }

    @Override
    String[] prepareParameters(String inputCommand) {
        return CorrectParameterChecker.
                getCorrectNumberOfParameters(this.getName(), inputCommand, 4);
    }

    @Override
    Object prepareDataToViewer(String[] parameters) {
        databaseManager.deleteRow(parameters[TABLE_NAME], parameters[COLUMN], parameters[VALUE]);
        String print = "row with  value \"" + parameters[VALUE] +
                "\" in field \"" + parameters[COLUMN] + "\" was removed from table \""
                + parameters[TABLE_NAME] + "\"";
        return new String[]{print, parameters[TABLE_NAME]};
    }

    @Override
    protected void viewResult(Object result) {
        super.viewResult(((String[]) result)[0]);
        databaseManager.selectAllFromTable(((String[]) result)[1]);
    }
}
