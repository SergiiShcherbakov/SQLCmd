package ua.com.juja.sergiishcherbakov.sqlcmd.controller.comand;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by StrannikFujitsu on 01.05.2017.
 */
public class menuCommandFactory {

    public Map<String, MenuCommand> getMenuCommand() {
        Map<String, MenuCommand> result = new HashMap<String, MenuCommand>();
        MenuCommand exit = new Exit();
        result.put(exit.getName(), exit);
        return result;
    }
}
