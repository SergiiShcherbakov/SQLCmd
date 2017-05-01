package ua.com.juja.sergiishcherbakov.sqlcmd.controller.comand;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLException;

/**
 * Created by StrannikFujitsu on 01.05.2017.
 */
public interface Command {

    String getName();

    String getDescription();

    boolean canProcess(String command);


    boolean process(Viewer viewer, DatabaseManager databaseManager, String inputCommand) throws SQLException, ClassNotFoundException;

}
