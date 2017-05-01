package ua.com.juja.sergiishcherbakov.sqlcmd.controller.comand;

/**
 * Created by StrannikFujitsu on 01.05.2017.
 */
public interface MenuCommand {

    String getName();

    String getDecription();

    boolean process( );

}
