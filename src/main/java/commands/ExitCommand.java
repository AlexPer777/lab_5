package commands;

import exceptions.InputException;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class ExitCommand extends Command implements Executable, Validatable {
    public ExitCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public void execute() {
        collectionManager.exit();
    }
    @Override
    public boolean isValid() {
        try {
            if (this.parameter == null) {
                return true;

            } else {
                throw new InputException("Exit mustn't be have a parameters");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid argument");
            return false;
        }
    }
}
