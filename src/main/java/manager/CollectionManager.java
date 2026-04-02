package manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import commands.LocalDateTimeAdapter;
import model.Car;
import model.HumanBeing;
import util.HumanBeingParser;
import util.RecursionManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class CollectionManager {
    public Vector<HumanBeing> collection = new Vector<>();
    public LocalDateTime initTime = LocalDateTime.now();
    public HashMap<Integer, HumanBeing> IDs = new HashMap<>();

    public void show() {
        for (HumanBeing humanBeing : collection) {
            System.out.println(humanBeing);
            System.out.println();
        }
    }
    public void clear(){
        collection.clear();
        IDs.clear();
        System.out.println("Clear command executed successfully");
    }
    public  void sort() {
        Collections.sort(collection);
        System.out.println("Коллекция отсортирована.");
    }
    public  void info(){
        System.out.println("Information about collections");
        System.out.println("Type: Human Being");
        System.out.println("Date initialization: " + initTime);
        System.out.println("Quantity elements: " + collection.size() + "\n");
    }
    public  void help(){
        System.out.println("""
help : вывести справку по доступным командам
info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
add {element} : добавить новый элемент в коллекцию
update id {element} : обновить значение элемента коллекции, id которого равен заданному
remove_by_id id : удалить элемент из коллекции по его id
clear : очистить коллекцию
save : сохранить коллекцию в файл
execute_script file_name : считать и исполнить скрипт из указанного файла. Для add, update id и remove_greater в скрипте поля элемента можно передавать следующими строками.
exit : завершить программу (без сохранения в файл)
remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)
remove_greater {element} : удалить из коллекции все элементы, превышающие заданный
sort : отсортировать коллекцию в естественном порядке
count_greater_than_car car : вывести количество элементов, значение поля car которых больше заданного
filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку
filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки
""");
    }
    public  void save(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();
        try (FileWriter writer = new FileWriter("collection.json")) {
            gson.toJson(collection, writer);
            System.out.println("Коллекция сохранена");
        } catch (IOException e) {
            System.out.println("Ошибка сохранения файла");
        }
    }
    public void add(Object parameter) {
        HumanBeing human;
        if (parameter != null && parameter.toString().startsWith("{")) {
            String raw = parameter.toString()
                    .replace("{", "")
                    .replace("}", "");
            human = HumanBeingParser.parse(raw);
            if (human == null) {
                System.out.println("Invalid element, skipping...");
                return;
            }
        } else {
            human = InputValidator.readHumanBeing();
        }
        collection.add(human);
        IDs.put(human.getId(), human);
        System.out.println("Element added successfully!");
    }
    public void countGreaterThanCar(){
        System.out.println("Введите данные машины для сравнения:");
        Car inputCar = InputValidator.readCar();
        int count = 0;
        for (HumanBeing human : collection) {
            Car car = human.getCar();
            if (car != null && car.compareTo(inputCar) > 0) {
                count++;
            }
        }
        System.out.println("Количество элементов: " + count);
    }
    public void executeScript(String fileName, Reader reader) {
        var controller = RecursionManager.getController();

        if (controller.checkRecursion(fileName)) {
            System.out.println("Recursion detected! Script already running: " + fileName);
            return;
        }
        try (Scanner fileScanner = new Scanner(new FileInputStream(fileName))) {
            controller.pushScript(fileName);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;
                line = prepareScriptCommand(line, fileScanner);
                if (line == null) {
                    return;
                }
                System.out.println(">> " + line);
                reader.getLine(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } finally {
            controller.popScript(fileName);
        }
    }
    private String prepareScriptCommand(String line, Scanner fileScanner) {
        if ("add".equals(line)) {
            String humanData = readScriptHumanBeingData(fileScanner, "add");
            return humanData == null ? null : "add {" + humanData + "}";
        }
        if ("remove_greater".equals(line)) {
            String humanData = readScriptHumanBeingData(fileScanner, "remove_greater");
            return humanData == null ? null : "remove_greater {" + humanData + "}";
        }
        if (line.startsWith("update ") && !line.contains("{")) {
            String[] parts = line.split("\\s+", 2);
            if (parts.length < 2 || parts[1].isBlank()) {
                System.out.println("Ошибка: для update в скрипте нужно указать id отдельной строкой команды");
                return null;
            }
            String humanData = readScriptHumanBeingData(fileScanner, "update");
            return humanData == null ? null : "update " + parts[1].trim() + " {" + humanData + "}";
        }
        return line;
    }

    private String readScriptHumanBeingData(Scanner fileScanner, String commandName) {
        List<String> values = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            String value = readNextScriptValue(fileScanner, commandName);
            if (value == null) {
                return null;
            }
            values.add(value);
        }

        String carName = values.get(8);
        if (!"null".equalsIgnoreCase(carName)) {
            String carCool = readNextScriptValue(fileScanner, commandName);
            if (carCool == null) {
                return null;
            }
            values.add(carCool);
        }

        return String.join(" ", values);
    }

    private String readNextScriptValue(Scanner fileScanner, String commandName) {
        while (fileScanner.hasNextLine()) {
            String value = fileScanner.nextLine().trim();
            if (value.isEmpty()) {
                continue;
            }
            return value;
        }
        System.out.println("Ошибка: в скрипте недостаточно данных для команды " + commandName);
        return null;
    }
    public void update(Object parameter) {
        if (parameter == null) {
            System.out.println("Не указан ID");
            return;
        }
        String input = parameter.toString().trim();
        String[] parts = input.split(" ", 2);
        int id;
        try {
            id = Integer.parseInt(parts[0]);
        } catch (Exception e) {
            System.out.println("ID должен быть числом");
            return;
        }
        if (!IDs.containsKey(id)) {
            System.out.println("Элемент с таким id не найден");
            return;
        }
        HumanBeing human = IDs.get(id);
        if (parts.length > 1 && parts[1].startsWith("{")) {
            String raw = parts[1]
                    .replace("{", "")
                    .replace("}", "");
            HumanBeing parsed = HumanBeingParser.parse(raw);
            if (parsed == null) {
                System.out.println("Invalid element, skipping...");
                return;
            }
            human.setName(parsed.getName());
            human.setCoordinates(parsed.getCoordinates());
            human.setRealHero(parsed.isRealHero());
            human.setHasToothpick(parsed.getHasToothpick());
            human.setImpactSpeed(parsed.getImpactSpeed());
            human.setWeaponType(parsed.getWeaponType());
            human.setMood(parsed.getMood());
            human.setCar(parsed.getCar());
        } else {
            System.out.println("Введите новые данные элемента:");

            human.setName(InputValidator.readName());
            human.setCoordinates(InputValidator.readCoordinates());
            human.setRealHero(InputValidator.readBoolean("Введите realHero"));
            human.setHasToothpick(InputValidator.readBoolean("Введите hasToothpick"));
            human.setImpactSpeed(InputValidator.readImpactSpeed());
            human.setWeaponType(InputValidator.readWeaponType());
            human.setMood(InputValidator.readMood());
            human.setCar(InputValidator.readCar());
        }
        System.out.println("Элемент успешно обновлён");
    }
    public void removeGreater(Object parameter) {
        HumanBeing example;
        if (parameter != null && parameter.toString().startsWith("{")) {
            String raw = parameter.toString()
                    .replace("{", "")
                    .replace("}", "");
            example = HumanBeingParser.parse(raw);
            if (example == null) {
                System.out.println("Invalid element");
                return;
            }
        } else {
            System.out.println("Введите данные элемента для сравнения:");
            example = InputValidator.readHumanBeing();
        }
        Iterator<HumanBeing> iterator = collection.iterator();
        int removed = 0;
        while (iterator.hasNext()) {
            HumanBeing human = iterator.next();
            if (human.compareTo(example) > 0) {
                iterator.remove();
                IDs.remove(human.getId());
                removed++;
            }
        }
        System.out.println("Удалено элементов: " + removed);
    }
    public void FilterContainsName(String substring) {
        int count = 0;
        for (HumanBeing human : collection) {
            if (human.getName().contains(substring)) {
                System.out.println(human);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Элементы не найдены.");
        }
    }
    public void FilterStartsWithName(String prefix) {
        int count = 0;
        for (HumanBeing human : collection) {
            if (human.getName().startsWith(prefix)) {
                System.out.println(human);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Элементы не найдены.");
        }
    }
    public void RemoveAt(Object parameter) {
        if (parameter == null) {
            System.out.println("Не указан индекс");
            return;
        }
        int index = Integer.parseInt(parameter.toString());
        if (index < 0 || index >= collection.size()) {
            System.out.println("Индекс вне диапазона коллекции");
            return;
        }
        HumanBeing humanBeing = collection.get(index);
        collection.remove(index);
        IDs.remove(humanBeing.getId());
        System.out.println("Элемент на позиции " + index + " удалён");
    }
    public void RemoveById(Object parameter) {
        if (parameter == null) {
            System.out.println("Не указан ID");
            return;
        }
        int id = Integer.parseInt(parameter.toString());
        if (!IDs.containsKey(id)) {
            System.out.println("Элемент с таким ID не найден");
            return;
        }
        HumanBeing humanBeing = IDs.get(id);
        collection.remove(humanBeing);
        IDs.remove(id);
        System.out.println("Элемент с id " + id + " успешно удалён");
    }
    public void exit()
    {
        System.out.println("Exit Command");
        System.exit(0);
    }
}
