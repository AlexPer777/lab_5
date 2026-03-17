package org.example;

import manager.InputManager;
import manager.CollectionLoader;
import model.HumanBeing;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Main {
    public static ArrayList<String> commandsList = new ArrayList<>();
    public static Vector<HumanBeing> collection = new Vector<>();
    public static LocalDateTime initTime = LocalDateTime.now();
    public static HashMap<Integer, HumanBeing> IDs = new HashMap<>();
    public static String FILE_NAME;
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            System.out.println("Не указан файл");
            System.exit(0);
        }
        FILE_NAME = args[0];
        CollectionLoader LC = new CollectionLoader();
        LC.loadCollection(args[0]);
        System.out.println("Program is running");
        InputManager.startInput();
    }
}
