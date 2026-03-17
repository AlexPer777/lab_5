package commands;

import exceptions.InputException;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class FilterStartsWithNameCommand extends Command implements Executable, Validatable {
    public FilterStartsWithNameCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public void execute() {
        collectionManager.FilterStartsWithName((String) parameter);
    }
    @Override
    public boolean isValid() {
        try {
            if (this.parameter != null) {
                return true;
            } else {
                throw new InputException("This command must have a arguments");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}