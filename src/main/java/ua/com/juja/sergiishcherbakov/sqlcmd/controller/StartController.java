package ua.com.juja.sergiishcherbakov.sqlcmd.controller;

import ua.com.juja.sergiishcherbakov.sqlcmd.controller.comand.Command;
import ua.com.juja.sergiishcherbakov.sqlcmd.controller.comand.MenuCommandFactory;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sergii Shcherbakov on 23.04.2017.
 * The class provide user registration and starts main menu
 */
public class StartController {
    private Viewer viewer;
    private DatabaseManager databaseManager;

    public StartController(Viewer viewer , DatabaseManager databaseManager ) {
        this.viewer = viewer;
        this.databaseManager = databaseManager;
    }

    public void start() throws Exception {
        welcome();
        while( true ){
            viewer.write("please enter your data in format:\"databaseName|userName|password\": ");

            String[] data = viewer.read(" ").split("[|]");
            try {
                if (data.length < 3) {
                    throw new IllegalArgumentException("3 parameters are expected but "+ data.length +" is entered" + " please, try again");
                }
                String databaseName = data[0];
                String login = data[1];
                String password = data[2];

                databaseManager.setConnection(databaseName, login, password); // can throw exception
                viewer.write("connect to database");

                List<Command> menuCommandMap = new MenuCommandFactory().getMenuCommand();
                new MainMenu(databaseManager, viewer, menuCommandMap).start();

                return;
            } catch (SQLException | IllegalArgumentException e ) {
                viewer.write(e.getMessage());
                viewer.write("please, try again");
            }
        }
    }

    private void welcome() {
        viewer.write("You started program SQLCmd from Sergii Shcherbakov");
        viewer.write("the program can to connect to your localhost database");
    }
}
