package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class RemoveByIdCommand extends Command implements Executable, Validatable {
    public RemoveByIdCommand(CollectionManager collectionManager){
        super(collectionManager);
    }
    @Override
    public void execute() {
        collectionManager.RemoveById(parameter);
    }
    @Override
    public boolean isValid() {
        return parameter != null;
    }
}
