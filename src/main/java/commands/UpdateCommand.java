package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class UpdateCommand extends Command implements Executable, Validatable {
    public UpdateCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public void execute(Object parameter) {
        collectionManager.update(parameter);
    }
    @Override
    public boolean isValid(Object parameter) {
        return requireUpdateArgument(parameter);
    }
}
