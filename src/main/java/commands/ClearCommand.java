package commands;

import exceptions.InputException;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;
import org.example.Main;

public class ClearCommand extends Command implements Executable, Validatable {
    public ClearCommand(Object parameter) {
        super(parameter);
    }
    @Override
    public boolean isValid() {
        try {
            if (this.parameter == null) {
                return true;
            } else {
                throw new InputException("Clear mustn't have a arguments");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    @Override
    public void execute() {
        Main.commandsList.add("clear");
        System.out.println("Clear command executed successfully");
        CollectionManager.clear();
    }
}
