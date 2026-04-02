package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class RemoveGreaterCommand extends Command implements Executable, Validatable {
    public RemoveGreaterCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public void execute(Object parameter) {
        collectionManager.removeGreater(parameter);
    }
    @Override
    public boolean isValid(Object parameter) {
        return requireHumanBeingArgumentOrNoArgs(parameter, "remove_greater {element}");
    }
}
