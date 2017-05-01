package ua.com.juja.sergiishcherbakov.sqlcmd.controller.comand;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import java.util.Collections;
import java.util.List;

/**
 * Created by StrannikFujitsu on 01.05.2017.
 */
public class HelpMenu implements  MenuCommand {
    private List<String> programDescription;

    public HelpMenu(List programDescription) {
        this.programDescription =  programDescription;
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
    public boolean process(Viewer viewer, DatabaseManager databaseManager) {
        viewer.write("The program suport next comand:");
        for (String menu: programDescription ) {
            viewer.write(menu);
        }
        return false;
    }
}
