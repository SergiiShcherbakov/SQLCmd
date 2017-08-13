package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;

import java.util.List;

/**
 * Created by Sergii Shcherbakov on 06.05.2017.
 */
public class FindCommand extends CommandSkeleton implements Command {

    public static final int TABLE_NAME = 1;

    public FindCommand() {
        super("find",
                "\tfind and print tables specified by user"+ System.lineSeparator() +
                        "\t\t\tformat the command:" + System.lineSeparator() +
                        "\t\t\tfind|tableName");
    }

    @Override
    String[] prepareParameters(String inputCommand) {
        return CorrectParameterChecker.getCorrectNumberOfParameters(this.getName(), inputCommand, 2);
    }

    @Override
    Object prepareDataToViewer(String[] parameters) {
        return  databaseManager.selectAllFromTable(parameters[TABLE_NAME]);
    }

    @Override
    protected void viewResult(Object result) {
        viewer.printTable( (List<List<String>>) result);
    }
}
