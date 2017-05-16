package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.exeptions.IncorrectNumberOfParametersException;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sergii Shcherbakov on 06.05.2017.
 */
public class ClearCommand extends CommandSkeleton implements Command {

    public ClearCommand() {
        super("clear",
                "\tclear table specified by user"+ System.lineSeparator() +
                        "\t\tformat the command:" + System.lineSeparator() +
                        "\t\t clear|\"table name\"");
    }

    @Override
    public boolean processAndExit(Viewer viewer, DatabaseManager databaseManager, String inputCommand) {
        try{
            String [] parameters = CorrectParameterChecker.
                    getCorrectNumberOfParameters(this.getName(), inputCommand, 2);
            if( databaseManager.clearTable(parameters[1])){
                viewer.write("table was cleared");
            }else{
                throw new SQLException("Table " + parameters[1] + " was not cleared.");
            }
        } catch (SQLException | IncorrectNumberOfParametersException  | ClassNotFoundException e ) {
            viewer.write(e.getMessage());
            viewer.write("please, try again");
        }
        return false;
    }
}
