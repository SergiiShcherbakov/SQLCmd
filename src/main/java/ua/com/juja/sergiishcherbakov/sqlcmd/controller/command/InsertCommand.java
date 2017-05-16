package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import com.sun.javafx.collections.MappingChange;
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
public class InsertCommand extends CommandSkeleton implements Command {

    public InsertCommand() {
        super("insert",
                "\tinsert row into table specified by user"+ System.lineSeparator() +
                        "\t\tformat the command:" + System.lineSeparator() +
                        "\t\t insert|\"table name\"|\"column1\"|\"value1\"|\"column2\"|\"value2\"|...|\"columnN\"|\"ValueN\"");
    }

    @Override
    public boolean processAndExit(Viewer viewer, DatabaseManager databaseManager, String inputCommand) {
        try{
            String [] parameters = CorrectParameterChecker.
                    getGetOddParameters(this.getName(), inputCommand);
            Map addRowToTable = new HashMap();
            StringBuilder row = new StringBuilder();
            for (int i = 2; i <parameters.length ; i+=2) {
                addRowToTable.put(parameters[i], parameters[i+1]);
                row.append(parameters[i] + "=" + parameters[i+1] + ",");
            }
            databaseManager.insertRow(parameters[1], addRowToTable);
            row.deleteCharAt(row.length()-1);
            viewer.write("row \"" +  row + "\"was added");
        } catch (SQLException | IncorrectNumberOfParametersException  | ClassNotFoundException e ) {
            viewer.write(e.getMessage());
            viewer.write("please, try again");
        }
        return false;
    }
}
