package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class CountGreaterThanCarCommand extends Command implements Executable, Validatable {
    public CountGreaterThanCarCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public void execute(Object parameter) {
        collectionManager.countGreaterThanCar();
    }
    @Override
    public boolean isValid(Object parameter) {
        return requireNoArguments(parameter, "count_greater_than_car");
    }
}
