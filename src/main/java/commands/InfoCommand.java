package commands;

import exceptions.InputException;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;
import org.example.Main;

public class InfoCommand extends Command implements Executable, Validatable {
    public InfoCommand(Object parameter) {
        super(parameter);
    }
    @Override
    public boolean isValid() {
        try {
            if (this.parameter == null) {
                return true;
            } else {
                throw new InputException("Info mustn't have a arguments");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    @Override
    public void execute() {
        Main.commandsList.add("info");
        System.out.println("Information about collections");
        CollectionManager.info();
    }

}
