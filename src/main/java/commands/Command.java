package commands;

import manager.CollectionManager;

public abstract class Command {

    protected final CollectionManager collectionManager;

    public Command(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    protected boolean requireNoArguments(Object parameter, String message) {
        if (parameter == null) {
            return true;
        }
        System.out.println(message);
        return false;
    }
    protected boolean requireArgument(Object parameter, String message) {
        if (parameter != null && !parameter.toString().isBlank()) {
            return true;
        }
        System.out.println(message);
        return false;
    }
    protected boolean requireHumanBeingArgumentOrNoArgs(Object parameter, String message) {
        if (parameter == null) {
            return true;
        }
        if (parameter.toString().startsWith("{")) {
            return true;
        }
        System.out.println(message);
        return false;
    }
    protected boolean requireNumericArgument(Object parameter, String message) {
        if (!requireArgument(parameter, message)) {
            return false;
        }
        if (parameter.toString().matches("\\d+")) {
            return true;
        }
        System.out.println(message);
        return false;
    }
    protected boolean requireUpdateArgument(Object parameter) {
        if (!requireArgument(parameter, "update id {element}")) {
            return false;
        }
        String[] parts = parameter.toString().trim().split("\\s+", 2);
        if (parts.length == 0 || !parts[0].matches("\\d+")) {
            System.out.println("update id {element}");
            return false;
        }
        if (parts.length == 1) {
            return true;
        }
        if (parts[1].startsWith("{")) {
            return true;
        }
        System.out.println("update id {element}");
        return false;
    }
    public abstract boolean isValid(Object parameter);
    public abstract void execute(Object parameter);
}
