package commands;

import exceptions.InputException;
import interfaces.Executable;
import interfaces.Validatable;
import manager.InputValidator;
import model.Car;
import model.HumanBeing;
import org.example.Main;

public class CountGreaterThanCarCommand extends Command implements Executable, Validatable {
    public CountGreaterThanCarCommand(Object parameter) {
        super(parameter);
    }
    @Override
    public void execute() {
        System.out.println("Введите данные машины для сравнения:");
        Car inputCar = InputValidator.readCar();
        int count = 0;
        for (HumanBeing human : Main.collection) {
            Car car = human.getCar();
            if (car != null && car.compareTo(inputCar) > 0) {
                count++;
            }
        }
        System.out.println("Количество элементов: " + count);
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