package ua.com.juja.sergiishcherbakov.sqlcmd.controller.service;


import java.util.List;

/**
 * Created by Sergii Shcherbakov on 05.09.2017.
 */
public interface Service {
    List<String> getCommandList();

    boolean isConnected();

    void connect(String database, String username, String password);

    void disconnect();

    boolean canExecute(String command);

    Object Execute(String command);
}
