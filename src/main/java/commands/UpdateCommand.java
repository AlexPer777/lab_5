package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.InputValidator;
import model.HumanBeing;
import org.example.Main;

public class UpdateCommand extends Command implements Executable, Validatable {

    public UpdateCommand(Object parameter) {
        super(parameter);
    }
    @Override
    public void execute() {
        if (parameter == null) {
            System.out.println("Не указан ID");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(parameter.toString());
        } catch (Exception e) {
            System.out.println("ID должен быть числом");
            return;
        }
        if (!Main.IDs.containsKey(id)) {
            System.out.println("Элемент с таким id не найден");
            return;
        }
        HumanBeing human = Main.IDs.get(id);
        System.out.println("Введите новые данные элемента:");
        human.setName(InputValidator.readName());
        human.setCoordinates(InputValidator.readCoordinates());
        human.setRealHero(InputValidator.readBoolean("Введите realHero"));
        human.setHasToothpick(InputValidator.readBoolean("Введите hasToothpick"));
        human.setImpactSpeed(InputValidator.readImpactSpeed());
        human.setWeaponType(InputValidator.readWeaponType());
        human.setMood(InputValidator.readMood());
        human.setCar(InputValidator.readCar());
        System.out.println("Элемент успешно обновлён");
    }

    @Override
    public boolean isValid() {
        return parameter != null;
    }
}