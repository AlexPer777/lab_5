package commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.InputException;
import interfaces.Executable;
import interfaces.Validatable;
import org.example.Main;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class SaveCommand extends Command implements Executable, Validatable {

    public SaveCommand(Object parameter) {
        super(parameter);
    }
    @Override
    public void execute() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();
        try (FileWriter writer = new FileWriter("collection.json")) {
            gson.toJson(Main.collection, writer);
            System.out.println("Коллекция сохранена");
        } catch (IOException e) {
            System.out.println("Ошибка сохранения файла");
        }
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