package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class ShowCommand extends Command implements Executable, Validatable {
    public ShowCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public boolean isValid(Object parameter) {
        return requireNoArguments(parameter, "show");
    }
    @Override
    public void execute(Object parameter) {
        collectionManager.show();
    }
}
