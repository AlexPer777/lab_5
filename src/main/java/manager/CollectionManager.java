package manager;

import model.HumanBeing;
import org.example.Main;

import java.time.LocalDateTime;
import java.util.Vector;

public class CollectionManager {
    private static Vector<HumanBeing> list = Main.collection;
    private final LocalDateTime initTime = LocalDateTime.now();
    public static void show(HumanBeing humanBeing) {
        System.out.println(humanBeing);
    }
    public static void clear(){
        Main.collection.clear();
        Main.IDs.clear();
    }
    public static void info(){
        System.out.println("Type: Human Being");
        System.out.println("Date initialization: " + Main.initTime);
        System.out.println("Quantity elements: " + list.size() + "\n");
    }
    public static Vector<HumanBeing> getCollection() {
        return Main.collection;
    }
}
