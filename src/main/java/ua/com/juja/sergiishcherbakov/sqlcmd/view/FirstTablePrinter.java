package ua.com.juja.sergiishcherbakov.sqlcmd.view;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sergii Shcherbakov on 13.05.2017.
 */
public class FirstTablePrinter implements TablePrinter {
    private Viewer viewer;

    public FirstTablePrinter(Viewer viewer) {
        this.viewer = viewer;
        viewer.setTablePrinter( this);
    }

    @Override
    public void printTable(List<List<String>> table) {
        int[] maxSizeOfColumn = getColumnsSizes(table);
        StringBuilder printedTable = new StringBuilder() ;
        printedTable.append(addSeparator(maxSizeOfColumn) );
        for (List<String> allColumn : table) {
            printedTable.append(addRow(allColumn, maxSizeOfColumn));
            printedTable.append(addSeparator(maxSizeOfColumn));
        }
        viewer.write(printedTable.toString());
    }

    private StringBuffer addSeparator(int[] maxSizeOfColumns) {
        StringBuffer result = new StringBuffer("+");
        for (int maxSizeOfColumn : maxSizeOfColumns) {
            result.append(fillChars(maxSizeOfColumn, '-'));
            result.append("+");
        }
        result.append( System.lineSeparator());
        return result;
    }

    private StringBuffer addRow(List<String> row, int[] maxSizeOfColumn) {
        StringBuffer result = new StringBuffer("|");
        char[] add;

        for (int i = 0; i < row.size(); i++) {
            if (row.get(i) != null) {
                add = fillChars(maxSizeOfColumn[i] - row.get(i).length(), ' ');
            } else {
                add = fillChars(maxSizeOfColumn[i] - "null".length(), ' ');
            }
            result.append(add);
            result.append(row.get(i)) ;
            result.append("|") ;
        }
        result.append(System.lineSeparator());
        return result;
    }


    private char[] fillChars(int size, char ch){
        char[] result = new char[size];
        Arrays.fill(result, ch);
        return result;
    }

    private int[] getColumnsSizes(List<List<String>> table) {
        int [] result = new int[table.get(0).size()];
        for (int i = 0; i < table.get(0).size(); i++) {
            result[i] =  getMaxLengthInColumn(table, i);
        }
        return result;
    }

    private int getMaxLengthInColumn(List<List<String>> table, int columnNumber ){
        int result = 0;
        int currentSize ;
        for (List<String> row : table) {
            if (row.get(columnNumber) != null) {
                currentSize = row.get(columnNumber).length();
                if (currentSize > result) result = currentSize;
            }
        }
        return result;
    }
}
