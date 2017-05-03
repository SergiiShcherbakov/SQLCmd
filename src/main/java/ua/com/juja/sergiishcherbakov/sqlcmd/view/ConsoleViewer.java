package ua.com.juja.sergiishcherbakov.sqlcmd.view;

import java.util.Scanner;

/**
 * Created by Sergii Shcherbakov  on 21.04.2017.
 */
 public class ConsoleViewer implements Viewer {

    @Override
    public void write(String string) {
        System.out.println(string);
    }

    @Override
    public String read() {
            Scanner sc = new Scanner(System.in);
            return sc.nextLine();
    }
}
