package commands;

import manager.CollectionManager;
import manager.Reader;

public class ExecuteScriptCommand extends Command {

    private final Reader reader;

    public ExecuteScriptCommand(CollectionManager collectionManager, Reader reader) {
        super(collectionManager);
        this.reader = reader;
    }
    @Override
    public void execute(Object parameter) {
        collectionManager.executeScript((String) parameter, reader);
    }
    @Override
    public boolean isValid(Object parameter) {
        return requireArgument(parameter, "execute_script file_name");
    }
}
