package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class RemoveAtCommand extends Command implements Executable, Validatable {
    public RemoveAtCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public void execute(Object parameter) {
        collectionManager.RemoveAt(parameter);
    }
    @Override
    public boolean isValid(Object parameter) {
        return requireNumericArgument(parameter, "remove_at index");
    }
}
