package ua.com.juja.sergiishcherbakov.sqlcmd.model.exeptions;

/**
 * Created by Sergii Shcherbakov on 11.05.2017.
 */
public class IncorrectNumberOfParametersException extends RuntimeException {
    public IncorrectNumberOfParametersException(String s) {
        super(s);
    }
}
