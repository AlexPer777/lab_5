package commands;

import interfaces.Executable;
import interfaces.Validatable;
import model.HumanBeing;
import org.example.Main;

public class RemoveByIdCommand extends Command implements Executable, Validatable {
    public RemoveByIdCommand(Object parameter){
        super(parameter);
    }
    @Override
    public void execute() {
        if (parameter == null) {
            System.out.println("Не указан ID");
            return;
        }
        int id = Integer.parseInt(parameter.toString());
        if (!Main.IDs.containsKey(id)) {
            System.out.println("Элемент с таким ID не найден");
            return;
        }
        HumanBeing humanBeing = Main.IDs.get(id);
        Main.collection.remove(humanBeing);
        Main.IDs.remove(id);
        System.out.println("Элемент с id " + id + " успешно удалён");
    }
    @Override
    public boolean isValid() {
        return parameter != null;
    }
}
