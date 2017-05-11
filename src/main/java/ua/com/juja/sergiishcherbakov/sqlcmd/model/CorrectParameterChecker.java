package ua.com.juja.sergiishcherbakov.sqlcmd.model;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.exeptions.IncorrectNumberOfParametersException;

/**
 * Created by Sergii Shcherbakov on 11.05.2017.
 */
public class CorrectParameterChecker {
    public static String[] getCorrectNumberOfParameters(String goodParameter, String inputCommand, int countParameters) throws IncorrectNumberOfParametersException {
        String[] data = inputCommand.split("[|]");
        if ( data.length != countParameters) {
            throw new IncorrectNumberOfParametersException( countParameters+ " parameters are expected but " +
                    data.length +
                    " is entered");
        }
        if (!data[0].toLowerCase().equals(goodParameter)) {
            throw new IncorrectNumberOfParametersException( "\"" + goodParameter +
                    "\" parameters are expected but \"" +
                    data[0] +
                    "\" is entered");
        } else {
            return data;
        }
    }
    //todo
//    public static String getCorrectNumberOfParameters(String goodParameter, String inputCommand) throws IncorrectNumberOfParametersException {
//    {}
}

