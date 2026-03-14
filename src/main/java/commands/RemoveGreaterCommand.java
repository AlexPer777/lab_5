package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.CollectionManager;
import manager.InputValidator;
import model.HumanBeing;
import java.util.Iterator;

public class RemoveGreaterCommand extends Command implements Executable, Validatable {
    public RemoveGreaterCommand(Object parameter) {
        super(parameter);
    }
    @Override
    public void execute() {
        System.out.println("Введите данные элемента для сравнения:");
        HumanBeing example = InputValidator.readHumanBeing();
        Iterator<HumanBeing> iterator = CollectionManager.getCollection().iterator();
        int removed = 0;
        while (iterator.hasNext()) {
            HumanBeing human = iterator.next();
            if (human.compareTo(example) > 0) {
                iterator.remove();
                removed++;
            }
        }
        System.out.println("Удалено элементов: " + removed);
    }
    @Override
    public boolean isValid() {
        return parameter == null;
    }
}