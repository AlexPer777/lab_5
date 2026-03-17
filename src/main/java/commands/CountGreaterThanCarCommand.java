package commands;

import exceptions.InputException;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class CountGreaterThanCarCommand extends Command implements Executable, Validatable {
    public CountGreaterThanCarCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public void execute() {
        collectionManager.countGreaterThanCar();
    }
    @Override
    public boolean isValid() {
        try {
            if (this.parameter == null) {
                return true;
            } else {
                throw new InputException("This command mustn't have a arguments");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}