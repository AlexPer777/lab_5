package manager;

import model.*;
import java.util.Scanner;

public class InputValidator {
    private final Scanner scanner;

    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readName() {
        while (true) {
            System.out.println("Введите name:");
            String name = scanner.nextLine();
            if (name == null || name.isBlank()) {
                System.out.println("Ошибка: имя не может быть пустым");
            } else {
                return name;
            }
        }
    }

    public boolean readBoolean(String message) {
        while (true) {
            System.out.println(message + " (true/false)");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Ошибка ввода. Введите true или false");
        }
    }

    public Integer readImpactSpeed() {
        while (true) {
            try {
                System.out.println("Введите impactSpeed (> -117):");
                int value = Integer.parseInt(scanner.nextLine());
                if (value <= -117) {
                    throw new IllegalArgumentException();
                }
                return value;
            } catch (Exception e) {
                System.out.println("Ошибка ввода. Попробуйте снова");
            }
        }
    }

    public Coordinates readCoordinates() {
        int x;
        long y;
        while (true) {
            try {
                System.out.println("Введите x:");
                x = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите y:");
                y = Long.parseLong(scanner.nextLine());
                return new Coordinates(x, y);
            } catch (Exception e) {
                System.out.println("Ошибка координат. Попробуйте снова");
            }
        }
    }

    public WeaponType readWeaponType() {
        while (true) {
            try {
                System.out.println("Введите weaponType (HAMMER, AXE, PISTOL, RIFLE, KNIFE):");
                return WeaponType.valueOf(scanner.nextLine().toUpperCase());
            } catch (Exception e) {
                System.out.println("Неверный тип оружия.");
            }
        }
    }
    public Mood readMood() {
        while (true) {
            try {
                System.out.println("Введите mood (SORROW, CALM, RAGE) или пусто:");
                String input = scanner.nextLine();
                if (input.isBlank()) {
                    return null;
                }
                return Mood.valueOf(input.toUpperCase());
            } catch (Exception e) {
                System.out.println("Неверное значение mood.");
            }
        }
    }

    public Car readCar() {
        System.out.println("Введите имя машины:");
        String name = scanner.nextLine();
        boolean cool = readBoolean("Машина крутая?");
        return new Car(name, cool);
    }

    public HumanBeing readHumanBeing(int id) {
        String name = readName();
        Coordinates coordinates = readCoordinates();
        boolean realHero = readBoolean("Введите realHero");
        boolean hasToothpick = readBoolean("Введите hasToothpick");
        Integer impactSpeed = readImpactSpeed();
        WeaponType weaponType = readWeaponType();
        Mood mood = readMood();
        Car car = readCar();
        return new HumanBeing(
                id,
                name,
                coordinates,
                realHero,
                hasToothpick,
                impactSpeed,
                weaponType,
                mood,
                car
        );
    }
}
