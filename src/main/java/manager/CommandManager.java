package manager;

import commands.*;

import java.util.HashMap;

public class CommandManager {

    private final HashMap<String, Command> commands = new HashMap<>();

    private final CollectionManager collectionManager;
    private Reader reader;

    public CommandManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    public void setReader(Reader reader) {
        this.reader = reader;
    }
    public void initCommands() {

        commands.put("help", new HelpCommand(collectionManager));
        commands.put("exit", new ExitCommand(collectionManager));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager));
        commands.put("update", new UpdateCommand(collectionManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commands.put("remove_at", new RemoveAtCommand(collectionManager));
        commands.put("save", new SaveCommand(collectionManager));
        commands.put("remove_greater", new RemoveGreaterCommand(collectionManager));
        commands.put("sort", new SortCommand(collectionManager));
        commands.put("count_greater_than_car", new CountGreaterThanCarCommand(collectionManager));
        commands.put("filter_contains_name", new FilterContainsNameCommand(collectionManager));
        commands.put("filter_starts_with_name", new FilterStartsWithNameCommand(collectionManager));
    }
    public void initScriptCommand() {
        commands.put("execute_script",
                new ExecuteScriptCommand(collectionManager, reader)
        );
    }
    public void startCommand(String command, Object parameter) {
        Command cmd = commands.get(command);
        if (cmd == null) {
            System.out.println("Unknown command: " + command);
            return;
        }
        if (cmd.isValid(parameter)) {
            cmd.execute(parameter);
        }
    }
}
