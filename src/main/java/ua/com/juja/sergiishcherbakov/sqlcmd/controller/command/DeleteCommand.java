package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;

/**
 * Created by Sergii Shcherbakov on 06.05.2017.
 */
class DeleteCommand extends CommandSkeleton implements Command {

    private static final int TABLE_NAME = 1;
    private static final int COLUMN = 2;
    private static final int VALUE = 3;

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
        String print = String.format("row with  value \"%s\" in field \"%s\" was removed from table \"%s\"",
                parameters[VALUE] , parameters[COLUMN], parameters[TABLE_NAME] );
        return new String[]{print, parameters[TABLE_NAME]};
    }

    @Override
    protected void viewResult(Object result) {
        super.viewResult(((String[]) result)[0]);
        databaseManager.selectAllFromTable(((String[]) result)[1]);
    }
}
