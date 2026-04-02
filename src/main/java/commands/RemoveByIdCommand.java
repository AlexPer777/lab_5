package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class RemoveByIdCommand extends Command implements Executable, Validatable {
    public RemoveByIdCommand(CollectionManager collectionManager){
        super(collectionManager);
    }
    @Override
    public void execute(Object parameter) {
        collectionManager.RemoveById(parameter);
    }
    @Override
    public boolean isValid(Object parameter) {
        return requireNumericArgument(parameter, "remove_by_id id");
    }
}
