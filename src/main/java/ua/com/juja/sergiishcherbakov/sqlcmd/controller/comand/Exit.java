package ua.com.juja.sergiishcherbakov.sqlcmd.controller.comand;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

/**
 * Created by StrannikFujitsu on 01.05.2017.
 */
public class Exit implements MenuCommand {

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return getName() +  "\texit from the program";
    }

    @Override
    public boolean process(Viewer viewer, DatabaseManager databaseManager) {
        return true;
    }
}
