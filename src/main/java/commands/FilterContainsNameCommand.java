package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class FilterContainsNameCommand extends Command implements Executable, Validatable {
    public FilterContainsNameCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public void execute(Object parameter) {
        collectionManager.FilterContainsName((String) parameter);
    }
    @Override
    public boolean isValid(Object parameter) {
        return requireArgument(parameter, "filter_contains_name name");
    }
}
