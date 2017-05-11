package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;
import java.sql.SQLException;

/**
 * Created by Sergii Shcherbakov on 01.05.2017.
 */
public interface Command {
//    todo create abstract command skeleton
    String getName();

    String getDescription();

    boolean canProcess(String command);


    boolean processAndExit(Viewer viewer, DatabaseManager databaseManager, String inputCommand);

}
