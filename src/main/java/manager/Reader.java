package manager;

import exceptions.InputException;

public class Reader {

    private final CollectionManager collectionManager;
    private final CommandManager commandManager;

    public Reader(CollectionManager collectionManager, CommandManager commandManager) {
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }
    public void getLine(String read) {
        try {
            if (read == null || read.trim().isEmpty()) {
                return;
            }
            read = read.trim();
            String command;
            String parameter = null;
            if (read.contains("{")) {
                int start = read.indexOf("{");
                int end = read.lastIndexOf("}");
                if (end == -1) {
                    throw new InputException("Ошибка: не закрыта скобка }");
                }
                String before = read.substring(0, start).trim();
                String body = read.substring(start, end + 1);
                String[] parts = before.split(" ");
                command = parts[0];
                if (parts.length > 1) {
                    parameter = parts[1] + " " + body;
                } else {
                    parameter = body;
                }
            } else {
                String[] parts = read.split(" ", 2);
                command = parts[0];
                parameter = (parts.length > 1) ? parts[1] : null;
            }
            validate(command, parameter);
            toCommand(command, parameter);
        } catch (InputException e) {
            System.out.println(e.getMessage());
        }
    }

    private void validate(String command, String parameter) throws InputException {
        if ("update".equals(command)) {
            if (parameter == null) {
                throw new InputException("update id {element}");
            }
            String[] parts = parameter.split(" ", 2);
            if (!parts[0].matches("\\d+")) {
                throw new InputException("update id {element}");
            }
        }
        if ("remove_greater".equals(command)) {
            if (parameter == null || !parameter.contains("{")) {
                throw new InputException("remove_greater {element}");
            }
        }
    }
    private void toCommand(String command, Object parameter) {
        commandManager.startCommand(command, parameter);
    }
}