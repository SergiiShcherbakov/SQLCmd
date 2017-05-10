package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Sergii Shcherbakov on 01.05.2017.
 */
public class MenuCommandFactory {


    public List <Command> getMenuCommand() {
        List<Command> result = getCommands();
        setHelpClass(result);
        return result;
}

    private List<Command> getCommands() {
        List<Command> result = new LinkedList<>();

        Reflections reflections = new Reflections( this.getClass().getPackage() );
        Set<Class<? extends Command>> allClasses = reflections.getSubTypesOf(Command.class);

        for (Class<? extends Command> classCommand : allClasses) {
                try {
                    if (!Modifier.isAbstract(classCommand.getModifiers())) {
                    result.add( classCommand.newInstance() );
                    }
                } catch (InstantiationException  | IllegalAccessException e ) {
                    //e.printStackTrace();
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
