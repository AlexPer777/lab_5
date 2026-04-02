package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class ExitCommand extends Command implements Executable, Validatable {
    public ExitCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public void execute(Object parameter) {
        collectionManager.exit();
    }
    @Override
    public boolean isValid(Object parameter) {
        return requireNoArguments(parameter, "exit");
    }
}
