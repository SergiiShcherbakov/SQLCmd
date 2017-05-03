package ua.com.juja.sergiishcherbakov.sqlcmd.view;

/**
 * Created by Sergii Shcherbakov  on 21.04.2017.
 */
public interface Viewer {

      void write(String string);

      String read();
}
