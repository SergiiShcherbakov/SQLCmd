package ua.com.juja.sergiishcherbakov.sqlcmd.view;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

/**
 * Created by Sergii Shcherbakov on 14.05.2017.
 */
public class FirstTablePrinterTest {
    @Test
    public void PrintTable() {
        // given
        ConsoleViewer console = Mockito.mock(ConsoleViewer.class);
        FirstTablePrinter printer = new FirstTablePrinter(console);

        List<List<String>> tab = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            List<String> row = new ArrayList<>();
            String add = "";
            for (int j = 0; j < 3; j++) {
                add = "";
                if(i % 2 == 0) {
                    add = "ttt";
                }
                if(j == 0 && i == 3 ) {
                    row.add(null);
                } else {
                    row.add("testTab row " + (i+1) + " column " + (j+1) + add );
                }
            }
            tab.add(row);
        }
        // when
        printer.printTable(tab);
        // then
        Mockito.verify(console).write(
        "+-------------------------+-------------------------+-------------------------+" + System.lineSeparator() +
        "|testTab row 1 column 1ttt|testTab row 1 column 2ttt|testTab row 1 column 3ttt|" + System.lineSeparator() +
        "+-------------------------+-------------------------+-------------------------+" + System.lineSeparator() +
        "|   testTab row 2 column 1|   testTab row 2 column 2|   testTab row 2 column 3|" + System.lineSeparator() +
        "+-------------------------+-------------------------+-------------------------+" + System.lineSeparator() +
        "|testTab row 3 column 1ttt|testTab row 3 column 2ttt|testTab row 3 column 3ttt|" + System.lineSeparator() +
        "+-------------------------+-------------------------+-------------------------+" + System.lineSeparator() +
        "|                     null|   testTab row 4 column 2|   testTab row 4 column 3|" + System.lineSeparator() +
        "+-------------------------+-------------------------+-------------------------+" + System.lineSeparator() +
        "|testTab row 5 column 1ttt|testTab row 5 column 2ttt|testTab row 5 column 3ttt|" + System.lineSeparator() +
        "+-------------------------+-------------------------+-------------------------+" + System.lineSeparator()  );
    }
}
