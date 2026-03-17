package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class UpdateCommand extends Command implements Executable, Validatable {
    public UpdateCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public void execute() {
        collectionManager.update(parameter);
    }
    @Override
    public boolean isValid() {
        return parameter != null;
    }
}