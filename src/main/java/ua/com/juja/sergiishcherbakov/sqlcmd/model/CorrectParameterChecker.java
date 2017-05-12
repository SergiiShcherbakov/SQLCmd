package ua.com.juja.sergiishcherbakov.sqlcmd.model;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.exeptions.IncorrectNumberOfParametersException;

/**
 * Created by Sergii Shcherbakov on 11.05.2017.
 */


public class CorrectParameterChecker {

    /**
     * The method checks is correct parameters in input command.
     * @param goodParameter - right first parameter.
     * @param inputCommand - all parameters in one string with CVS separation. Separator "|".
     * @param countParameters - right count of parameters.
     * @return string array with right parameters.
     * @throws IncorrectNumberOfParametersException
     *       with message  "@param countParameters" parameters are expected but
     *       "entered count parameters" is entered" if input wrong count parameters.
     *       And with message
     *       "@param goodParameter" parameters are expected but "bad first parameter"  is entered"
     *       if input bad first parameter
     */
    public static String[] getCorrectNumberOfParameters(String goodParameter, String inputCommand, int countParameters)
            throws IncorrectNumberOfParametersException {
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

