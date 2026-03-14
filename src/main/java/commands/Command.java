package commands;

import interfaces.Executable;
import interfaces.Validatable;

public class Command implements Executable, Validatable {
    public Object parameter;
    public Command(Object parameter) {
        this.parameter = parameter;
    }
    @Override
    public boolean isValid() {
        return true;
    }
    @Override
    public void execute() {
        System.out.println("Command not found");
    }
}
