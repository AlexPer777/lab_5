package commands;

import manager.CollectionManager;
import manager.Reader;

public class ExecuteScriptCommand extends Command {

    private final CollectionManager collectionManager;
    private Reader reader;

    public ExecuteScriptCommand(CollectionManager collectionManager, Reader reader) {
        super(collectionManager);
        this.collectionManager = collectionManager;
        this.reader = reader;
        ;
    }

    @Override
    public void execute() {
        collectionManager.executeScript((String) parameter, reader);
    }

    @Override
    public boolean isValid() {
        return parameter != null;
    }
}