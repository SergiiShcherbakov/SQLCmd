package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

/**
 * Created by Sergii Shcherbakov on 10.05.2017.
 */
public abstract class CommandSkeleton implements Command {

    private final String name;
    private final String description;
    Viewer viewer;
    DatabaseManager databaseManager;

    private void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    private void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    CommandSkeleton(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return String.format("%s\t%s", name , description);
    }

    @Override
    public boolean canProcess( String  command) {
        return command.toLowerCase().startsWith(name);
    }

    @Override
    public final boolean processAndExit(Viewer viewer, DatabaseManager databaseManager, String inputCommand) {
        setDatabaseManager(databaseManager);
        setViewer(viewer);
        try {
            String [] parameters = prepareParameters( inputCommand);
            Object result = prepareDataToViewer( parameters);
            viewResult( result);
        } catch ( RuntimeException e  ) {
            viewer.write(e.getMessage());
            viewer.write("please, try again");
            return false;
        }
        return isLastCommand();
    }

    boolean canProcessWithoutParameters(String command) {
        return command.toLowerCase().equals(name);
    }

    abstract  String [] prepareParameters(String inputCommand);

    abstract  Object prepareDataToViewer(String [] parameters);

    boolean isLastCommand(){
        return false;
    }

    void viewResult(Object result){
         viewer.write((String) result);
     }
}
