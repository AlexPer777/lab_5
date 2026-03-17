package commands;

import exceptions.InputException;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class ShowCommand extends Command implements Executable, Validatable {
    public ShowCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public boolean isValid() {
        if (collectionManager.collection.isEmpty()) {
            System.out.println("Collection doesn't have elements!");
        }
        try {
            if (this.parameter == null) {
                return true;
            } else {
                throw new InputException("Show doesn't have arguments!");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    @Override
    public void execute() {
        collectionManager.show();
    }
}
