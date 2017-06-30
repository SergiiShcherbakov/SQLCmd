package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by Sergii Shcherbakov on 14.06.2017.
 */
public class MenuCommandFactoryTest {
        MenuCommandFactory factory = new MenuCommandFactory();
        List<Command> commmandList = factory.getMenuCommand();

    @Test
    public void countOfCommandsTest(){
        //given
        int expectedCountOfCommands = 11;
        //when
        int size = commmandList.size();
        //then
        assertEquals(expectedCountOfCommands, size);
    }

    @Test
    public void hasHelpCommandTest(){
        //given
        String testString = "help";
        boolean canProcess = false;
        //when
        for (Command command : commmandList) {
           if( command.canProcess(testString)) {
               canProcess = true;
           }
        }
        //then
        assertTrue(canProcess);
    }

    @Test
    public void hasExitCommandTest(){
        //given
        String testString = "exit";
        boolean canProcess = false;
        //when
        for (Command command : commmandList) {
           if( command.canProcess(testString)) {
               canProcess = true;
           }
        }
        //then
        assertTrue(canProcess);
    }

}