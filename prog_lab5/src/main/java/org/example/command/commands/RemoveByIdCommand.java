package org.example.command.commands;

import org.example.command.Command;
import org.example.command.ConsoleOutput;
import org.example.managers.CollectionManager;

/**
 * Класс команды remove_by_id
 */
public class RemoveByIdCommand extends Command {
    private final ConsoleOutput consoleOutput;
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("remove_by_id", "удаляет элемент из коллекции по его id");
        this.consoleOutput = consoleOutput;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            consoleOutput.printError("Команда принимает 1 целочисленный аргумент");
            return;
        }
        try {
            int id = Integer.parseInt(args[0]);
            if (collectionManager.removeById(id)) {
                consoleOutput.println("Элемент с id=" + id + " был успешно удален из коллекции!!");
                return;
            }
            consoleOutput.println("Элемента с id=" + id + " не существует");
        } catch (NumberFormatException e) {
            consoleOutput.printError("Команда принимает только 1 целочисленный аргумент");
        }
    }
}
