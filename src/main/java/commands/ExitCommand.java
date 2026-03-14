package commands;

import exceptions.InputException;
import interfaces.Executable;
import interfaces.Validatable;
import org.example.Main;

import java.util.InputMismatchException;

public class ExitCommand extends Command implements Executable, Validatable {
    public ExitCommand(Object parameter) {
        super(parameter);
    }
    @Override
    public boolean isValid() {
        try {
            if (this.parameter == null) {
                return true;

            } else {
                throw new InputException("Exit mustn't be have a parameters");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid argument");
            return false;
        }
    }
    @Override
    public void execute() {
        Main.commandsList.add("exit");
        System.out.println("Exit Command");
        System.exit(0);
    }
}
