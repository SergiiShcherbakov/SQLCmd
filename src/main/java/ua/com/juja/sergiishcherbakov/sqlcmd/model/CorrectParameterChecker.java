package ua.com.juja.sergiishcherbakov.sqlcmd.model;

import ua.com.juja.sergiishcherbakov.sqlcmd.model.exeptions.IncorrectNumberOfParametersException;

/**
 * Created by Sergii Shcherbakov on 11.05.2017.
 */


public class CorrectParameterChecker {

    public static final int FIRST_PARAM = 0;

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
        if (isCorrectFirstParameter( goodParameter, data[FIRST_PARAM]))  {
            return data;
        } else {
            //newer
            throw new IncorrectNumberOfParametersException("");
        }
    }

    public static String[] getCorrectNumberOfParameters( String inputCommand, int countParameters)
            throws IncorrectNumberOfParametersException {
        String[] parameters = inputCommand.split("[|]");
        if ( parameters.length != countParameters) {
            throw new IncorrectNumberOfParametersException( countParameters+ " parameters are expected but " +
                    parameters.length +
                    " is entered");
        }
            return parameters;
    }

    public static String[] getCorrectNumberOfParametersOrMore(String goodParameter, String inputCommand,
                                                              int countParameters)
            throws IncorrectNumberOfParametersException {
        String[] data = inputCommand.split("[|]");
        if ( data.length < countParameters) {
            throw new IncorrectNumberOfParametersException(
                    String.format( "Insert wrong number of parameters. " +
                                    "Minimum %s parameters are expected but %s parameters are entered"
                            , countParameters , data.length));
        }
        if (isCorrectFirstParameter( goodParameter, data[FIRST_PARAM]))  {
            return data;
        } else {
            //newer
            throw new IncorrectNumberOfParametersException("");
        }
    }

    public static String getCorrectParameter(String name, String inputCommand)
            throws IncorrectNumberOfParametersException {
        return getCorrectNumberOfParameters(name, inputCommand, 1)[FIRST_PARAM];
    }



    public static String[] getGetOddParameters(String goodFirstParameter, String inputCommand, int minCountParameters) throws IncorrectNumberOfParametersException {
        String[] data = inputCommand.split("[|]");
        isCorrectFirstParameter(goodFirstParameter, data[FIRST_PARAM]);
        if(data.length < minCountParameters){
            throw new IncorrectNumberOfParametersException(
                    "insert wrong number of parameters. Minimum " + minCountParameters + " parameters are expected and " +
                            + data.length + " parameters are entered");
        }
        if ( data.length % 2 != 0 ) {
            throw new IncorrectNumberOfParametersException(
                    "insert wrong number of parameters. An even number of parameters are expected and an odd are entered");
        }
        return data;
    }

    private static boolean isCorrectFirstParameter(String goodFirstParameter, String parameter) throws IncorrectNumberOfParametersException {
        if (!parameter.toLowerCase().equals(goodFirstParameter)) {
            throw new IncorrectNumberOfParametersException("\"" + goodFirstParameter +
                    "\" parameter are expected but \"" +
                    parameter +
                    "\" is entered");
        } else {
            return true;
        }
    }
}

