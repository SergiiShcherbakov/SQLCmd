package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.exeptions.IncorrectNumberOfParametersException;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLException;

/**
 * Created by Sergii Shcherbakov on 06.05.2017.
 */
public class DeleteCommand extends CommandSkeleton implements Command {

    public DeleteCommand() {
        super("delete",
                "\tdelete row from table by name and value specified by user "+ System.lineSeparator() +
                        "\t\tformat the command:" + System.lineSeparator() +
                        "\t\t delete|tableName|column|value");
    }

    @Override
    public boolean processAndExit(Viewer viewer, DatabaseManager databaseManager, String inputCommand) {
        try{
            String [] parameters = CorrectParameterChecker.
                    getCorrectNumberOfParameters(this.getName(), inputCommand, 4);
            databaseManager.deleteRowFromTable(parameters[1], parameters[2], parameters[3]);
            viewer.write("row with  value \"" +  parameters[3] +
                    "\" in field \""+ parameters[2] +"\" was removed from table \""
                    + parameters[1] + "\"" );
            databaseManager.selectAllFromTable(parameters[1]);
        } catch (SQLException | IncorrectNumberOfParametersException  | ClassNotFoundException e ) {
            viewer.write(e.getMessage());
            viewer.write("please, try again");
        }
        return false;
    }
}
