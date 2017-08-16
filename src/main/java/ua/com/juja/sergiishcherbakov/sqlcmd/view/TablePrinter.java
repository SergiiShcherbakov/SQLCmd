package ua.com.juja.sergiishcherbakov.sqlcmd.view;

import java.util.List;

/**
 * Created by Sergii Shcherbakov on 13.05.2017.
 */
interface TablePrinter {
    void printTable(List<List<String>> table);
}
