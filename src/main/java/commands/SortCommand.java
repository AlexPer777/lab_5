package commands;

import exceptions.InputException;
import interfaces.Executable;
import interfaces.Validatable;
import org.example.Main;
import java.util.Collections;

public class SortCommand extends Command implements Executable, Validatable {
    public SortCommand(Object parameter) {
        super(parameter);
    }
    @Override
    public void execute() {
        Collections.sort(Main.collection);
        System.out.println("Коллекция отсортирована.");
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