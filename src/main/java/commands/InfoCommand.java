package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class InfoCommand extends Command implements Executable, Validatable {
    public InfoCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public boolean isValid(Object parameter) {
        return requireNoArguments(parameter, "info");
    }
    @Override
    public void execute(Object parameter) {
        collectionManager.info();
    }

}
