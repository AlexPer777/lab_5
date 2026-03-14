package commands;

import exceptions.InputException;
import interfaces.Executable;
import interfaces.Validatable;
import manager.InputValidator;
import model.*;
import org.example.Main;


public class AddCommand extends Command implements Executable, Validatable {
    public AddCommand(Object parameter) {
        super(parameter);
    }
    @Override
    public void execute() {
        HumanBeing human = InputValidator.readHumanBeing();
        Main.collection.add(human);
        Main.IDs.put(human.getId(), human);
        System.out.println("Элемент добавлен!");
    }
    @Override
    public boolean isValid() {
        try {
            if (this.parameter == null) {
                return true;
            } else {
                throw new InputException("Add mustn't have a arguments");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
