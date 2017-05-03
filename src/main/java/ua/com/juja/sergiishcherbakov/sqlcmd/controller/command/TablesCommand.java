package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;
import java.sql.SQLException;

/**
 * Created by Sergii Shcherbakov on 01.05.2017.
 */
public class TablesCommand implements Command {
    @Override
    public String getName() {
        return "tables";
    }

    @Override
    public String getDescription() {
        return getName() +
                "\tdisplays all tables in the database";
    }

    @Override
    public boolean canProcess(String command) {
        String newCommand = new String(command);
        newCommand.toLowerCase();
        return newCommand.equals("tables");
    }

    @Override
    public boolean processAndExit(Viewer viewer,
                                  DatabaseManager databaseManager,
                                  String inputCommand) {
        try {
            viewer.write(databaseManager.getTablesNames().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
