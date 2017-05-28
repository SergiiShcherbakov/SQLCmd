package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import javafx.util.Pair;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.exeptions.IncorrectNumberOfParametersException;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergii Shcherbakov on 06.05.2017.
 */
public class UpdateCommand extends CommandSkeleton implements Command {

    public UpdateCommand() {
        super("update",
                "\tupdate table where column 1 = value1 " + System.lineSeparator() +
                        "\t\twith values 2 ... value n in column 2 - column n specified by user" + System.lineSeparator() +
                        "\t\tformat the command:" + System.lineSeparator() +
                        "\t\tupdate|tableName|column1|value1|column2|value2|...|columnN|valueN");
    }

    @Override
    public boolean processAndExit(Viewer viewer, DatabaseManager databaseManager, String inputCommand) {
        try{
            String [] parameters = CorrectParameterChecker.
                    getGetOddParameters(this.getName(), inputCommand, 6);
            Map updateColumnValues = new HashMap();
            StringBuilder row = new StringBuilder();
            for (int i = 4; i <parameters.length ; i+=2) {
                updateColumnValues.put(parameters[i], parameters[i+1]);
            }
            databaseManager.updateTable(parameters[1], new Pair<>(parameters[2], parameters[3]), updateColumnValues);
            viewer.write("in table \"" + parameters[1]
                    + "\" was updated row(s) with column \"" + parameters[2] +
                    "\"=\"" + parameters[3] + "\"" );
            List<List <String>> table = databaseManager.selectAllFromTable(parameters[1]);
            viewer.printTable(table);
        } catch (SQLException | IncorrectNumberOfParametersException  | ClassNotFoundException e ) {
            viewer.write(e.getMessage());
            viewer.write("please, try again");
        }
        return false;
    }
}
