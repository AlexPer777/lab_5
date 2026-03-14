package commands;

import interfaces.Executable;
import interfaces.Validatable;
import model.HumanBeing;
import org.example.Main;

public class RemoveAtCommand extends Command implements Executable, Validatable {
    public RemoveAtCommand(Object parameter) {
        super(parameter);
    }
    @Override
    public void execute() {
        if (parameter == null) {
            System.out.println("Не указан индекс");
            return;
        }
        int index = Integer.parseInt(parameter.toString());
        if (index < 0 || index >= Main.collection.size()) {
            System.out.println("Индекс вне диапазона коллекции");
            return;
        }
        HumanBeing humanBeing = Main.collection.get(index);
        Main.collection.remove(index);
        Main.IDs.remove(humanBeing.getId());
        System.out.println("Элемент на позиции " + index + " удалён");
    }
    @Override
    public boolean isValid() {
        return parameter != null;
    }
}
