package manager;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputManager {
    public static Scanner consoleRead = new Scanner(System.in);
    private static final InputStream DEFAULT_IN = System.in;

    public static String readInput() {
        if (consoleRead.hasNextLine()) {
            return consoleRead.nextLine();
        } else {
            restoreStandardInput();
            return consoleRead.nextLine();
        }
    }
    public static void restoreStandardInput() {
        System.setIn(DEFAULT_IN);
        InputManager.consoleRead = new Scanner(System.in);
    }
    public static void startInput() {
        try {
            do {
                String ConsoleRead = readInput();
                Reader.getLine(ConsoleRead);
            } while (true);
        } catch (NoSuchElementException e) {
            System.out.println("Error");
            System.exit(0);
        }
    }
}
