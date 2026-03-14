package commands;

import interfaces.Executable;
import interfaces.Validatable;
import manager.InputManager;
import manager.Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteScriptCommand extends Command implements Executable, Validatable {
    public ExecuteScriptCommand(Object parameter) {
        super(parameter);
    }
    @Override
    public void execute() {
        String fileName = (String) parameter;
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println("> " + line);
                Reader.getLine(line);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        }
        InputManager.restoreStandardInput();
    }
    @Override
    public boolean isValid() {
        return parameter != null;
    }
}