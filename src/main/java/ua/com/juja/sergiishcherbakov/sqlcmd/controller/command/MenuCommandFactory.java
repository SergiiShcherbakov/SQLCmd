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

    public List <Command> getMenuCommand(Class classs) {
        List<Command> result = getCommands(classs);
        setHelpClass(result);
        return result;
}

    private List<Command> getCommands(Class classs ) {
        List<Command> result = new LinkedList<>();

        Reflections reflections = new Reflections( this.getClass().getPackage() );
        Set<Class<? extends Command>> allClasses = reflections.getSubTypesOf(classs);

        for (Class<? extends Command> classCommand : allClasses) {
                try {
                    if (!Modifier.isAbstract(classCommand.getModifiers())) {
                    result.add( classCommand.newInstance() );
                    }
                } catch (InstantiationException  | IllegalAccessException e ) {
                    throw new RuntimeException(e.getCause());
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
