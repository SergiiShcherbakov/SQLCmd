package ua.com.juja.sergiishcherbakov.sqlcmd.view;

import java.util.*;

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
        int[] maxSizeOfColumn = getColumnsSizes(table);
        StringBuffer printedTable = new StringBuffer() ;
        printedTable.append(addSeparator(maxSizeOfColumn) );
        for (int i = 0; i <table.size() ; i++) {
            printedTable.append( addRow(table.get(i), maxSizeOfColumn));
            printedTable.append(addSeparator(maxSizeOfColumn));
        }
        viewer.write(printedTable.toString());
    }

    private StringBuffer addSeparator(int[] maxSizeOfColumn) {
        StringBuffer result = new StringBuffer("+");
        for (int i = 0; i < maxSizeOfColumn.length; i++) {
            result.append(fillChars(maxSizeOfColumn[i],'-'));
            result.append("+");
        }
        result.append( System.lineSeparator());
        return result;
    }
    private StringBuffer addRow(List<String> row, int[] maxSizeOfColumn) {
        StringBuffer result = new StringBuffer("|");
        char[] add;

        for (int i = 0; i < row.size(); i++) {
            add = fillChars(maxSizeOfColumn[i] - row.get(i).length(), ' ');
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

    private int getMaxLengthInColumn(List<List<String>> table, int column ){
        int result = 0;
        int currentSize ;
        for (int i = 0; i < table.size(); i++) {
            currentSize = table.get(i).get(column).length();
            if( currentSize > result ) result = currentSize;
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<String>> tab = new LinkedList<>();
        FirstTablePrinter fp = new FirstTablePrinter(new ConsoleViewer());

        for (int i = 0; i < 22; i++) {
        List<String> row = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                row.add("testTab row " + (i+1) + " column " +
                        (j+1) +
                        Arrays.toString( fp.fillChars(new Random().nextInt(2), '_')) );
            }
         tab.add(row);
        }
        fp.printTable(tab);
    }
}
