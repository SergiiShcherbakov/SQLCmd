package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sergii Shcherbakov on 01.05.2017.
 */
public class HelpMenuCommand implements Command, HelpMenu {
    private List<String> programDescription;

    public HelpMenuCommand(){}

    public HelpMenuCommand(List<Command> menuCommand) {
        setCommand(menuCommand);
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
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return getName() +
                "\tget name of command that support the program";
    }

    @Override
    public boolean canProcess(String command) {
        String newCommand = new String(command);
        newCommand.toLowerCase();
        return newCommand.equals(getName());
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
