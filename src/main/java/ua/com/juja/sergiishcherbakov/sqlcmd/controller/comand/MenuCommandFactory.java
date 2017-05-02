package ua.com.juja.sergiishcherbakov.sqlcmd.controller.comand;


import org.reflections.Reflections;
import sun.reflect.Reflection;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * Created by StrannikFujitsu on 01.05.2017.
 */
public class MenuCommandFactory {

    public List <Command> getMenuCommand() {
//        List<Command> result = new LinkedList<Command>();
//
//        Command tables = new TablesCommand();
//        result.add(tables);
//
//        Command exit = new ExitCommand();
//        result.add(exit);
//
//        Command help = new HelpMenu(result);
//        result.add(help);



        return setClases();
    }

    private   List <Command>   setClases(){
        List<Command> result = new LinkedList<Command>();
        Reflections reflections = new Reflections("ua.com.juja.sergiishcherbakov.sqlcmd.controller.comand");

        Set<Class<? extends Command>> allClasses =
                reflections.getSubTypesOf(Command.class);
        Class<? extends Command> helpClass = null;
        for (Class<? extends Command> c: allClasses) {
            if (c.getCanonicalName().equals("ua.com.juja.sergiishcherbakov.sqlcmd.controller.comand.HelpMenu")) {
                helpClass = c;
            }
            else{
                try {
                    result.add((Command) c.newInstance() );
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        if (helpClass != null) {
            try {
                HelpMenu helpMenu =(HelpMenu) helpClass.newInstance();
                result.add(helpMenu);
                helpMenu.setCommand(result);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

       return result;
    }

    public static void main(String[] args) {
        new MenuCommandFactory().setClases();
    }
}
