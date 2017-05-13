package ua.com.juja.sergiishcherbakov.sqlcmd.view;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Sergii Shcherbakov  on 21.04.2017.
 */
 public class ConsoleViewer implements Viewer {

    private TablePrinter tablePrinter;

    @Override
    public void setTablePrinter(TablePrinter tablePrinter) {
        this.tablePrinter = tablePrinter;
    }

    @Override
    public void printTable(List<List<String>> table) {
        tablePrinter.printTable(table);
    }

    @Override
    public void write(String string) {
        System.out.println(string);
    }

    @Override
    public String read() {
            Scanner sc = new Scanner(System.in);
            return sc.nextLine();
    }
}
