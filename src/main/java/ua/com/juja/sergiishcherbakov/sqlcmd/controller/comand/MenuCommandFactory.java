package ua.com.juja.sergiishcherbakov.sqlcmd.controller.comand;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by StrannikFujitsu on 01.05.2017.
 */
public class MenuCommandFactory {

    public List <Command> getMenuCommand() {
        List<Command> result = new LinkedList<Command>();

        Command exit = new ExitCommand();
        result.add(exit);

        Command help = new HelpMenu(result);
        result.add(help);



        return result;
    }
}
