package ua.com.juja.sergiishcherbakov.sqlcmd.view;

/**
 * Created by StrannikFujitsu on 21.04.2017.
 */
public interface Viewer {

      void write(String string);

      String read(String string);
}
