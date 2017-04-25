package sqlcmd.view;

import org.junit.Before;
import org.junit.Test;

import sqlcmd.ConsoleMock;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.ConsoleViewer;
import ua.com.juja.sergiishcherbakov.sqlcmd.view.Viewer;

import static org.junit.Assert.assertEquals;

/**
 * Created by StrannikFujitsu on 22.04.2017.
 */
public class ConsoleViewerTest {

    ConsoleMock consoleMock;
    Viewer viewer;
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
        assertEquals( viewer.read( " "), expected );
    }

    @Test
    public void WrongTestInput(){
        // given
        String vrong = "____";
        String expected = "skjdfkl";
        // when
        consoleMock.addIn(vrong);

       // System.out.println(viewer.read(""));

        // then
        assertEquals( false, viewer.read( " ").equals( expected) );
    }

     @Test
    public void TestOutput(){
        // given
        String expected = "skjdfkl";
        // when
         viewer.write(expected);

       // System.out.println(viewer.read(""));

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

        // System.out.println(viewer.read(""));

        // then
        assertEquals( expected + "\r\n" +
                        expected + "\r\n" ,
                consoleMock.getOut() );
    }



}
