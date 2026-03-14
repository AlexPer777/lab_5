package commands;

import exceptions.InputException;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;
import model.HumanBeing;
import org.example.Main;

public class ShowCommand extends Command implements Executable, Validatable {
    public ShowCommand(Object parameter) {
        super(parameter);
    }
    @Override
    public boolean isValid() {
        if (Main.collection.size() == 0) {
            System.out.println("Collection doesn't have elements!");
        }
        try {
            if (this.parameter == null) {
                return true;
            } else {
                throw new InputException("Show doesn't have arguments!");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    @Override
    public void execute() {
        Main.commandsList.add("show");
        for (HumanBeing humanBeing : Main.collection) {
            System.out.println(Main.collection.indexOf(humanBeing));
            CollectionManager.show(humanBeing);
            System.out.println();
        }
    }
}
