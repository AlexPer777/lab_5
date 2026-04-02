package manager;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ApplicationRunner {

    public void run(String[] args) {
        if (args.length == 0) {
            System.out.println("Не указан файл");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        CollectionManager collectionManager = new CollectionManager(scanner);
        CollectionLoader loader = new CollectionLoader(collectionManager);
        try {
            loader.loadCollection(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + args[0]);
            return;
        }

        CommandManager commandManager = new CommandManager(collectionManager);
        Reader reader = new Reader(collectionManager, commandManager);

        commandManager.setReader(reader);
        commandManager.initCommands();
        commandManager.initScriptCommand();

        startInteractiveMode(reader, scanner);
    }

    private void startInteractiveMode(Reader reader, Scanner scanner) {
        System.out.println("Program is running");
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
