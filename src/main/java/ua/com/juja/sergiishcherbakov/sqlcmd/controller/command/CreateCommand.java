package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.exeptions.IncorrectNumberOfParametersException;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sergii Shcherbakov on 30.05.2017.
 */
public class CreateCommand extends CommandSkeleton {

    public CreateCommand() {
        super("create",
                "\tcreate new table specified by user"+ System.lineSeparator() +
                        "\t\tformat the command:" + System.lineSeparator() +
                        "\t\tcreate|tableName|column1|column2|...|columnN");
    }

    @Override
    public boolean processAndExit(Viewer viewer, DatabaseManager databaseManager, String inputCommand) {
        try{
            String [] parameters = CorrectParameterChecker.
                    getCorrectNumberOfParametersOrMore(this.getName(), inputCommand, 3);
            StringBuilder columnsName = new StringBuilder();
            List<String> columns = new LinkedList<>();
            for (int i = 2; i <parameters.length ; i++) {
                columns.add(parameters[i]);
                columnsName.append(parameters[i]).append(", ");
            }
            if(databaseManager.createTableWithoutTypesColumn(parameters[1], columns)){
                columnsName.deleteCharAt(columnsName.length()-1);
                columnsName.deleteCharAt(columnsName.length()-1);
                viewer.write(
                        String.format("table with name \"%s\" and with column \"%s\" was added to current database",
                                parameters[1], columnsName) );
            }
        } catch (SQLException | IncorrectNumberOfParametersException | ClassNotFoundException e ) {
            viewer.write(e.getMessage());
            viewer.write("please, try again");
        }
        return false;
    }
}
