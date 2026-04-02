package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class ClearCommand extends Command implements Executable, Validatable {
    public ClearCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public void execute(Object parameter) {
        collectionManager.clear();
    }
    @Override
    public boolean isValid(Object parameter) {
        return requireNoArguments(parameter, "clear");
    }
}
