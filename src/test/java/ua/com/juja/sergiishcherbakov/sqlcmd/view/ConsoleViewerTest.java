package ua.com.juja.sergiishcherbakov.sqlcmd.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.com.juja.sergiishcherbakov.sqlcmd.ConsoleMock;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by StrannikFujitsu on 22.04.2017.
 */
public class ConsoleViewerTest {

    private ConsoleMock consoleMock;
    private Viewer viewer;

    @Before
    public void set(){
         consoleMock = new ConsoleMock();
         viewer = new ConsoleViewer();
    }

    @Test
    public void TestInput(){
        // given
        String expected = "kjhasfkhkalsh";
        // when
        consoleMock.addIn(expected);

        // then
        assertEquals( viewer.read( ), expected );
    }

    @Test
    public void WrongTestInput(){
        // given
        String vrong = "____";
        String expected = "skjdfkl";
        // when
        consoleMock.addIn(vrong);
        // then
        assertEquals( false, viewer.read( ).equals( expected) );
    }

     @Test
    public void TestOutput(){
        // given
        String expected = "skjdfkl";
        // when
         viewer.write(expected);
        // then
        assertEquals( expected + "\r\n",
                consoleMock.getOut() );
    }

    @Test
    public void TestDoubleOutput(){
        // given
        String expected = "skjdfkl";
        // when
        viewer.write(expected);
        viewer.write(expected);
        // then
        assertEquals( expected + "\r\n" +
                        expected + "\r\n" ,
                consoleMock.getOut() );
    }

    @Test
    public void TestSetAndPrintInTablePrinter(){
        // given
        TablePrinter printer =  mock(TablePrinter.class);
        List<List<String>> table = new LinkedList<>();
        viewer.setTablePrinter(printer);
        // when
        viewer.printTable(table);
        // then
       Mockito.verify(printer).printTable(table);
    }
}
