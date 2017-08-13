package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;

/**
 * Created by Sergii Shcherbakov on 01.05.2017.
 */
public class TablesCommand extends CommandSkeleton implements Command  {

    public TablesCommand() {
        super("tables", "\tdisplays all tables in the database");
    }

    @Override
    String[] prepareParameters(String inputCommand) {
        CorrectParameterChecker.getCorrectParameter(getName(), inputCommand);
        return new String[0];
    }

    @Override
    Object prepareDataToViewer(String[] parameters) {
        return databaseManager.getTablesNames().toString();
    }

    @Override
    public boolean canProcess(String command) {
        return canProcessWithoutParameters(command);
    }
}
