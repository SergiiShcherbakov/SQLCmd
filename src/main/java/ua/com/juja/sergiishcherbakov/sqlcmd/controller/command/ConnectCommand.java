package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

/**
 * Created by Sergii Shcherbakov on 06.05.2017.
 */
public class ConnectCommand extends CommandSkeleton implements Command {

    private static final int DB_NAME = 1;
    private static final int LOGIN = 2;
    private static final int PASSWORD = 3;
    private static final int COMMAND_NAME = 0;

    public ConnectCommand() {
        super("connect",
                "\tconnect to database specified by user"+ System.lineSeparator() +
                        "\t\t\tformat the command:" + System.lineSeparator() +
                        "\t\t\tconnect|database|user|password");
    }

    @Override
    String[] prepareParameters(String inputCommand) {
        return CorrectParameterChecker.getCorrectNumberOfParameters(this.getName(), inputCommand, 4);
    }

    @Override
    Object prepareDataToViewer(String[] parameters) {
        databaseManager.closeConnection();
        databaseManager.setConnection(parameters[DB_NAME], parameters[LOGIN], parameters[PASSWORD]);
        return String.format( "connection to database %s is successful", parameters[DB_NAME]);
    }


    public boolean connectToDB(Viewer viewer, DatabaseManager databaseManager, String inputCommand){
        try{
            String [] parameters = CorrectParameterChecker.
                    getCorrectNumberOfParameters( inputCommand, PASSWORD);
            databaseManager.closeConnection();
            if (databaseManager.setConnection(parameters[COMMAND_NAME], parameters[DB_NAME], parameters[LOGIN])) {
                viewer.write( String.format("connection to database %s is successful", parameters[COMMAND_NAME] ));
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
