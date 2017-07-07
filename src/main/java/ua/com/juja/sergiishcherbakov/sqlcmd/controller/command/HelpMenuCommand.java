package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.CorrectParameterChecker;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.exeptions.IncorrectNumberOfParametersException;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sergii Shcherbakov on 01.05.2017.
 */
public class HelpMenuCommand extends CommandSkeleton implements  HelpMenu, Command {

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
    String[] prepareParameters(String inputCommand) {
        CorrectParameterChecker.getCorrectParameter(getName(), inputCommand);
        return new String[]{getName()};
    }

    @Override
    Object prepareDataToViewer(String[] parameters) {
        return null;
    }

    @Override
    protected void viewResult(Object result) {
        viewer.write("The program support next command:");
        for (String pointOfMenu : programDescription ) {
            viewer.write( pointOfMenu );
        }
    }

    @Override
    public boolean canProcess(String command) {
        return canProcessWithoutParameters(command);
    }
}
