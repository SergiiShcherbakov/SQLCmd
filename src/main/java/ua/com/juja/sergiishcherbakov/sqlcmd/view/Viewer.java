package ua.com.juja.sergiishcherbakov.sqlcmd.view;

import java.util.List;

/**
 * Created by Sergii Shcherbakov  on 21.04.2017.
 */
public interface Viewer {

      void write(String string);

      String read();

      void printTable(List<List<String>> table);

      void setTablePrinter(TablePrinter tablePrinter);
}
