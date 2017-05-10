package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLException;

/**
 * Created by Sergii Shcherbakov on 10.05.2017.
 */
public abstract class CommandSkeleton implements Command {

    String name;
    String description;

    public CommandSkeleton(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return name + "\t" + description;
    }

    @Override
    public boolean canProcess( String  command) {
        String newString = new String(command).toLowerCase();
        return newString.startsWith(name);
    }

    @Override
    abstract public boolean processAndExit(Viewer viewer, DatabaseManager databaseManager, String inputCommand) throws SQLException, ClassNotFoundException ;
}