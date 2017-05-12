package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.exeptions.IncorrectNumberOfParametersException;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;
import java.sql.SQLException;

/**
 * Created by Sergii Shcherbakov on 06.05.2017.
 */
public class DropCommand extends CommandSkeleton implements Command {

    public DropCommand() {
        super("drop",
                "\tremove tables specified by user"+ System.lineSeparator() +
                        "\t\tformat the command:" + System.lineSeparator() +
                        "\t\t drop|\"table name\"");
    }

    @Override
    public boolean processAndExit(Viewer viewer, DatabaseManager databaseManager, String inputCommand) {
        try{
            String [] parameters = CorrectParameterChecker.
                    getCorrectNumberOfParameters(this.getName(), inputCommand, 2);
            databaseManager.deleteTable(parameters[1]);
            viewer.write(parameters[1] + " was removed" );
            return false;
        } catch (SQLException | IncorrectNumberOfParametersException  | ClassNotFoundException e ) {
            viewer.write(e.getMessage());
            viewer.write("please, try again");
        }
        return false;
    }
}
