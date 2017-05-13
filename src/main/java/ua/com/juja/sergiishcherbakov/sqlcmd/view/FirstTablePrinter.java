package ua.com.juja.sergiishcherbakov.sqlcmd.view;

import java.util.List;

/**
 * Created by Sergii Shcherbakov on 13.05.2017.
 */
public class FirstTablePrinter implements TablePrinter {
    private Viewer viewer;

    public FirstTablePrinter(Viewer viewer) {
        this.viewer = viewer;
    }

    @Override
    public void printTable(List<List<String>> table) {
        throw new RuntimeException("it`s not work");
    }
}
