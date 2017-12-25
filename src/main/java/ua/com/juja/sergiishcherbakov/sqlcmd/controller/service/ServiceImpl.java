package ua.com.juja.sergiishcherbakov.sqlcmd.controller.service;

import ua.com.juja.sergiishcherbakov.sqlcmd.controller.command.Command;
import ua.com.juja.sergiishcherbakov.sqlcmd.controller.command.MenuCommandFactory;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.database.JDBCPostgresSQLDatabaseManager;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sergii Shcherbakov on 05.09.2017.
 */
public class ServiceImpl implements  Service{

    private static Service service;
    private final List<Command> commands;
    private final DatabaseManager dbManager;

    private ServiceImpl() {
        commands = new MenuCommandFactory().getMenuCommand(Command.class);
        dbManager = new JDBCPostgresSQLDatabaseManager();
    }


    public synchronized static Service getInstance() {
        if (service == null) {
            service =  new ServiceImpl();
        }
        return service;
    }

    @Override
    public List<String> getCommandList() {
        List<String>  result = new LinkedList<>();
        for (Command command: commands) {
            result.add(command.getName());
        }
        return result;
    }

    @Override
    public boolean isConnected() {
        return dbManager.isConnect();
    }

    @Override
    public void connect(String database, String username, String password) {
        dbManager.setConnection(database, username, password);
    }

    @Override
    public void disconnect() {
        dbManager.closeConnection();
    }

    @Override
    public boolean canExecute(String inputCommand) {
        for (Command command : commands) {
            if (command.canProcess(inputCommand)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object Execute(String inputCommand) {
        for (Command command : commands) {
            if (command.canProcess(inputCommand)) {
               return command.processAndExit(dbManager, inputCommand );
            }
        }
        throw new RuntimeException("The command can`t  execute.");
    }


}
