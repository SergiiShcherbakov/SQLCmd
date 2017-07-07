package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.exeptions.IncorrectNumberOfParametersException;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLException;

/**
 * Created by Sergii Shcherbakov on 06.05.2017.
 */
public class ConnectCommand extends CommandSkeleton implements Command {

    public ConnectCommand() {
        super("connect",
                "\tconnect to database specified by user"+ System.lineSeparator() +
                        "\t\tformat the command:" + System.lineSeparator() +
                        "\t\t connect|database|user|password");
    }

    @Override
    String[] prepareParameters(String inputCommand) {
        return CorrectParameterChecker.getCorrectNumberOfParameters(this.getName(), inputCommand, 4);
    }

    @Override
    Object prepareDataToViewer(String[] parameters) {
        databaseManager.closeConnection();
        databaseManager.setConnection(parameters[1], parameters[2], parameters[3]);
        return  "connection to database " + parameters[1] + " is successful";
    }


    public boolean connectToDB(Viewer viewer, DatabaseManager databaseManager, String inputCommand){
        try{
            String [] parameters = CorrectParameterChecker.
                    getCorrectNumberOfParameters( inputCommand, 3);
            databaseManager.closeConnection();
            if (databaseManager.setConnection(parameters[0], parameters[1], parameters[2])) {
                viewer.write( "connection to database " + parameters[0] + " is successful" );
                return true;
            }

        } catch ( RuntimeException e ) {
            viewer.write(e.getMessage());
            viewer.write("please, try again");
            return false;
        }
        return false;
    }
}
