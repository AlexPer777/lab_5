package org.example;

import manager.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            System.out.println("Не указан файл");
            return;
        }
        CollectionManager collectionManager = new CollectionManager();
        CollectionLoader loader = new CollectionLoader(collectionManager);
        loader.loadCollection(args[0]);
        CommandManager commandManager = new CommandManager(collectionManager);
        Reader reader = new Reader(collectionManager, commandManager);
        commandManager.setReader(reader);
        commandManager.initCommands();
        commandManager.initScriptCommand();
        System.out.println("Program is running");
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line == null || line.trim().isEmpty()) {
                continue;
            }
            reader.getLine(line);
        }
        System.out.println("EOF detected. Program terminated");
    }
}