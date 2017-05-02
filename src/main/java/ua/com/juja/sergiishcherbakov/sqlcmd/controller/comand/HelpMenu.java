package ua.com.juja.sergiishcherbakov.sqlcmd.controller.comand;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by StrannikFujitsu on 01.05.2017.
 */
public class HelpMenu implements Command {
    private List<String> programDescription;

    public HelpMenu(){}

    public HelpMenu(List<Command> menuComand) {


    }

    public void setCommand(List<Command> menuCommand){
        this.programDescription = new LinkedList<String>();
        for (Command c :
                menuCommand) {
            programDescription.add(c.getDescription());
        }
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return getName() +  "\tget name of command that support the program";
    }

    @Override
    public boolean canProcess(String command) {
        String newCommand = new String(command);
        newCommand.toLowerCase();
        return newCommand.equals("help");
    }


    @Override
    public boolean process(Viewer viewer, DatabaseManager databaseManager, String inputCommand) {
        viewer.write("The program suport next comand:");
        for (String menu: programDescription ) {
            viewer.write(menu);
        }
        return false;
    }
}
