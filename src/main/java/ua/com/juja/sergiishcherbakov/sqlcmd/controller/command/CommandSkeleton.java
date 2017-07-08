package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.exeptions.IncorrectNumberOfParametersException;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.sql.SQLException;

/**
 * Created by Sergii Shcherbakov on 10.05.2017.
 */
public abstract class CommandSkeleton implements Command {

    String name;
    String description;
    Viewer viewer;
    DatabaseManager databaseManager;

    private void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    private void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

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
        return command.toLowerCase().startsWith(name);
    }

    @Override
    public boolean processAndExit(Viewer viewer, DatabaseManager databaseManager, String inputCommand) {
        setDatabaseManager(databaseManager);
        setViewer(viewer);
        try {
            String [] parameters = prepareParameters( inputCommand);
            Object result = prepareDataToViewer( parameters);
            viewResult( result);
        } catch ( RuntimeException e  ) {
            viewer.write(e.getMessage());
            viewer.write("please, try again");
        }
        return false;
    }

    protected boolean canProcessWithoutParameters(String command) {
        return command.toLowerCase().equals(name);
    }

    abstract  String [] prepareParameters(String inputCommand);

    abstract  Object prepareDataToViewer(String [] parameters);

    protected void viewResult(Object result){
         viewer.write((String) result);
     }
}
