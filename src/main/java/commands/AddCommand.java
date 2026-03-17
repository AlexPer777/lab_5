package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class AddCommand extends Command implements Executable, Validatable {

    public AddCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute() {
        collectionManager.add(parameter);
    }
    @Override
    public boolean isValid() {
        return true;
    }
}