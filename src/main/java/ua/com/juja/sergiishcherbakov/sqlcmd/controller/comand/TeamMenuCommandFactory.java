package ua.com.juja.sergiishcherbakov.sqlcmd.controller.comand;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by StrannikFujitsu on 01.05.2017.
 */
public class TeamMenuCommandFactory {

    public Map<String, MenuCommand> getMenuCommand() {
        Map<String, MenuCommand> result = new HashMap<String, MenuCommand>();
        List<String> helpMenuDescription = new LinkedList<>();

        MenuCommand exit = new Exit();
        helpMenuDescription.add(exit.getDescription());
        result.put(exit.getName(), exit);

        MenuCommand help = new HelpMenu(helpMenuDescription);
        helpMenuDescription.add(help.getDescription());
        result.put(help.getName(), help );



        return result;
    }
}
