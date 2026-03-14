package commands;

import exceptions.InputException;
import interfaces.Executable;
import interfaces.Validatable;
import model.HumanBeing;
import org.example.Main;

public class FilterStartsWithNameCommand extends Command implements Executable, Validatable {
    public FilterStartsWithNameCommand(Object parameter) {
        super(parameter);
    }
    @Override
    public void execute() {
        String prefix = (String) parameter;
        int count = 0;
        for (HumanBeing human : Main.collection) {
            if (human.getName().startsWith(prefix)) {
                System.out.println(human);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Элементы не найдены.");
        }
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