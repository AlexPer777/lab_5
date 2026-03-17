package manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import commands.LocalDateTimeAdapter;
import model.HumanBeing;
import org.example.Main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Vector;

public class CollectionLoader {
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setPrettyPrinting()
            .create();
    public void loadCollection(String fileName) throws FileNotFoundException {
        try (FileReader reader = new FileReader(fileName)) {
            Type listType = new TypeToken<Vector<HumanBeing>>(){}.getType();
            Vector<HumanBeing> humanBeings = gson.fromJson(reader, listType);
            int maxId = 0;
            if (humanBeings != null) {
                for (HumanBeing humanBeing : humanBeings) {
                        Main.collection.add(humanBeing);
                        Main.IDs.put(humanBeing.getId(), humanBeing);
                        if (humanBeing.getId() > maxId) {
                            maxId = humanBeing.getId();
                        }
                }
                HumanBeing.setIdCounter(maxId + 1);
            }
        } catch (IOException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}
