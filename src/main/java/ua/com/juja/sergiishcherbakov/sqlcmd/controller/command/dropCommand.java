package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;
import java.sql.SQLException;

/**
 * Created by Sergii Shcherbakov on 06.05.2017.
 */
public class dropCommand implements Command{
    @Override
    public String getName() {
        return "drop";
    }

    @Override
    public String getDescription() {
        return getName() +
                "\tremove tables specified by user"+ System.lineSeparator() +
                "\tformat the command:" + System.lineSeparator() +
                "\t" + getName() + "|\"table name\"";
    }

    @Override
    public boolean canProcess(String command) {
        String newCommand = new String(command);
        newCommand.toLowerCase();
        return newCommand.startsWith(getName());
    }

    @Override
    public boolean processAndExit(Viewer viewer, DatabaseManager databaseManager, String inputCommand) throws SQLException, ClassNotFoundException {
        String[] data = inputCommand.split("[|]");
        try {
            if ( data.length != 2) {
                throw new IllegalArgumentException("2 parameters are expected but " +
                        data.length +
                        " is entered" +
                        " please, try again");
            }
            String tableName = data[1];
            databaseManager.deleteTable(tableName);
            viewer.write(tableName +" was removed" );
            return false;
        } catch (SQLException | IllegalArgumentException e ) {
            viewer.write(e.getMessage());
            viewer.write("please, try again");
        }
        return false;
    }
}
