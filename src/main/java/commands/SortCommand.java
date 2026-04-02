package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;


public class SortCommand extends Command implements Executable, Validatable {
    public SortCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public void execute(Object parameter) {
        collectionManager.sort();
    }
    @Override
    public boolean isValid(Object parameter) {
        return requireNoArguments(parameter, "sort");
    }
}
