package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;

public class FilterStartsWithNameCommand extends Command implements Executable, Validatable {
    public FilterStartsWithNameCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public void execute(Object parameter) {
        collectionManager.FilterStartsWithName((String) parameter);
    }
    @Override
    public boolean isValid(Object parameter) {
        return requireArgument(parameter, "filter_starts_with_name name");
    }
}
