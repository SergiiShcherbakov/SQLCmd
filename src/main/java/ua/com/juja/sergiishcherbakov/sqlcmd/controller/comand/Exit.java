package ua.com.juja.sergiishcherbakov.sqlcmd.controller.comand;

/**
 * Created by StrannikFujitsu on 01.05.2017.
 */
public class Exit implements MenuCommand {

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDecription() {
        return getName() +  "\t If you want exit from the program";
    }

    @Override
    public boolean process() {
        return true;
    }
}
