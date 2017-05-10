package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sergii Shcherbakov on 01.05.2017.
 */
public class HelpMenuCommand extends CommandSkeleton implements  HelpMenu {

    private List<String> programDescription;

    public HelpMenuCommand(){
        super("help",
                "\tget name and description of command that support the program");
    }

    @Override
    public void setCommand(List<Command> menuCommand){
        this.programDescription = new LinkedList<>();
        for (Command c : menuCommand) {
            programDescription.add( c.getDescription() );
        }
        Collections.sort(programDescription);
    }


    @Override
    public boolean processAndExit(Viewer viewer, DatabaseManager databaseManager, String inputCommand) {
        viewer.write("The program support next command:");
        for (String pointOfMenu : programDescription ) {
            viewer.write( pointOfMenu );
        }
        return false;
    }
}
