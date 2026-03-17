package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;


public class SortCommand extends Command implements Executable, Validatable {
    public SortCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }
    @Override
    public void execute() {
        collectionManager.sort();
    }
    @Override
    public boolean isValid() {
        if (parameter != null) {
            System.out.println("This command mustn't have arguments");
            return false;
        }
        return true;
    }
}