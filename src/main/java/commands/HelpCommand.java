package commands;

import exceptions.InputException;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class HelpCommand extends Command implements Executable, Validatable {
    public HelpCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public boolean isValid() {
        try {
            if (this.parameter == null) {
                return true;
            } else {
                throw new InputException("Help mustn't have a parameters");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    @Override
    public void execute() {
        collectionManager.help();
    }
}

