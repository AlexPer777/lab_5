package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class HelpCommand extends Command implements Executable, Validatable {
    public HelpCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public boolean isValid(Object parameter) {
        return requireNoArguments(parameter, "help");
    }
    @Override
    public void execute(Object parameter) {
        collectionManager.help();
    }
}
