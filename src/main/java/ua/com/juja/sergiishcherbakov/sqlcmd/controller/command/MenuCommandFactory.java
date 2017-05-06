package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import org.reflections.Reflections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Sergii Shcherbakov on 01.05.2017.
 */
public class MenuCommandFactory {

    private static final String COMMAND_PACKAGE_NAME = "ua.com.juja.sergiishcherbakov.sqlcmd.controller.command";

    public List <Command> getMenuCommand() {
        return setClasses();
}

    private   List <Command> setClasses(){

        List<Command> result = getCommands();
        setHelpClass(result);
        return result;
    }

    private List<Command> getCommands() {
        List<Command> result = new LinkedList<>();

        Reflections reflections = new Reflections(COMMAND_PACKAGE_NAME);
        Set<Class<? extends Command>> allClasses = reflections.getSubTypesOf(Command.class);

        for (Class<? extends Command> classCommand : allClasses) {
                try {
                    result.add( classCommand.newInstance() );
                } catch (InstantiationException  | IllegalAccessException e ) {
                    e.printStackTrace();
                }
        }
        return result;
    }

    private void setHelpClass(List<Command> result) {
        for (Command command : result) {
            if (command instanceof HelpMenu) {
                ((HelpMenu) command).setCommand(result);
            }
        }
    }
}
