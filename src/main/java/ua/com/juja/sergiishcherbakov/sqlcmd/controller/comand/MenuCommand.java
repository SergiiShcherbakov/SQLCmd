package ua.com.juja.sergiishcherbakov.sqlcmd.controller.comand;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

/**
 * Created by StrannikFujitsu on 01.05.2017.
 */
public interface MenuCommand {

    String getName();

    String getDescription();

    boolean process(Viewer viewer, DatabaseManager databaseManager);

}
