package manager;

import model.*;
import java.util.Scanner;

public class InputValidator {
    private static final Scanner scanner = new Scanner(System.in);
    public static String readName() {
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

    public static boolean readBoolean(String message) {
        while (true) {
            System.out.println(message + " (true/false)");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Ошибка ввода. Введите true или false");
        }
    }

    public static Integer readImpactSpeed() {
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

    public static Coordinates readCoordinates() {
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

    public static WeaponType readWeaponType() {
        while (true) {
            try {
                System.out.println("Введите weaponType (HAMMER, AXE, PISTOL, RIFLE, KNIFE):");
                return WeaponType.valueOf(scanner.nextLine().toUpperCase());
            } catch (Exception e) {
                System.out.println("Неверный тип оружия.");
            }
        }
    }
    public static Mood readMood() {
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

    public static Car readCar() {
        System.out.println("Введите имя машины:");
        String name = scanner.nextLine();
        boolean cool = readBoolean("Машина крутая?");
        return new Car(name, cool);
    }
    public static HumanBeing readHumanBeing() {
        String name = readName();
        Coordinates coordinates = readCoordinates();
        boolean realHero = readBoolean("Введите realHero");
        boolean hasToothpick = readBoolean("Введите hasToothpick");
        Integer impactSpeed = readImpactSpeed();
        WeaponType weaponType = readWeaponType();
        Mood mood = readMood();
        Car car = readCar();
        return new HumanBeing(
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