package ua.com.juja.sergiishcherbakov.sqlcmd.controller.command;

/**
 * Created by Sergii Shcherbakov on 16.08.2017.
 */
public class UnsupportedCommand extends CommandSkeleton  {

    public static final int INPUT_WRONG_COMMAND = 0;

    public UnsupportedCommand() {
        super("", "");
    }

    @Override
    public boolean canProcess(String command) {
        return true;
    }

    @Override
    String[] prepareParameters(String inputCommand) {
        return new String[] {inputCommand};
    }

    @Override
    Object prepareDataToViewer(String[] parameters) {
        return String.format( "Command \"%s\" does not supported.", parameters[INPUT_WRONG_COMMAND]);
    }
}
