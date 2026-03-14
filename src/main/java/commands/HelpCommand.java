package commands;

import exceptions.InputException;
import interfaces.Executable;
import interfaces.Validatable;
import org.example.Main;

public class HelpCommand extends Command implements Executable, Validatable {
    public HelpCommand(Object parameter) {
        super(parameter);
    }
    @Override
    public boolean isValid() {
        try {
            if (this.parameter == null) {
                return true;
            } else {
                throw new InputException("Help mustn't have a parameters");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    @Override
    public void execute() {
        Main.commandsList.add("help");
        System.out.println("""
help : вывести справку по доступным командам
info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
add {element} : добавить новый элемент в коллекцию
update id {element} : обновить значение элемента коллекции, id которого равен заданному
remove_by_id id : удалить элемент из коллекции по его id
clear : очистить коллекцию
save : сохранить коллекцию в файл
execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
exit : завершить программу (без сохранения в файл)
remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)
remove_greater {element} : удалить из коллекции все элементы, превышающие заданный
sort : отсортировать коллекцию в естественном порядке
count_greater_than_car car : вывести количество элементов, значение поля car которых больше заданного
filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку
filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки
""");
    }
}

