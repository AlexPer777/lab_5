package manager;

import commands.*;
import model.*;
import java.util.HashMap;

public class CommandManager {
    private final HashMap<String, Command> commands = new HashMap<>();
    public  CommandManager(Object parameter) {
        commands.put("help", new HelpCommand(parameter));
        commands.put("exit", new ExitCommand(parameter));
        commands.put("show", new ShowCommand(parameter));
        commands.put("clear", new ClearCommand(parameter));
        commands.put("info", new InfoCommand(parameter));
        commands.put("add", new AddCommand(parameter));
        commands.put("update", new UpdateCommand(parameter));
        commands.put("remove_by_id", new RemoveByIdCommand(parameter));
        commands.put("remove_at", new RemoveAtCommand(parameter));
        commands.put("save", new SaveCommand(parameter));
        commands.put("execute_script", new ExecuteScriptCommand(parameter));
        commands.put("remove_greater", new RemoveGreaterCommand(parameter));
        commands.put("sort", new SortCommand(parameter));
        commands.put("count_greater_than_car", new CountGreaterThanCarCommand(parameter));
        commands.put("filter_contains_name", new FilterContainsNameCommand(parameter));
        commands.put("filter_starts_with_name", new FilterStartsWithNameCommand(parameter));
    }
    public void startCommand(String command) {
        if (commands.containsKey(command)) {
            Command cmd = commands.get(command);
            if (cmd.isValid()) {
                cmd.execute();
            } else {
                InputManager.startInput();
            }
        } else {
            Command cmd = new Command(null);
            cmd.execute();
        }
    }

}
