package ua.com.juja.sergiishcherbakov.sqlcmd.controller.service;


import java.util.List;

/**
 * Created by Sergii Shcherbakov on 05.09.2017.
 */
public interface Service {
    public List<String> getCommandList();

    public boolean isConnected();
}
