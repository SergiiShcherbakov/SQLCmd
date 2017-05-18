package ua.com.juja.sergiishcherbakov.sqlcmd.view;

import java.util.List;

/**
 * Created by Sergii Shcherbakov on 13.05.2017.
 */
public interface TablePrinter {
    //todo change List<List<String>> table by class Table
    void printTable(List<List<String>> table);
}
