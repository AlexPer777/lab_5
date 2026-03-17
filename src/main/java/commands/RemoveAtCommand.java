package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class RemoveAtCommand extends Command implements Executable, Validatable {
    public RemoveAtCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public void execute() {
        collectionManager.RemoveAt(parameter);
    }
    @Override
    public boolean isValid() {
        return parameter != null;
    }
}
