package org.example.command.commands;

import org.example.command.Command;
import org.example.command.ConsoleOutput;
import org.example.managers.CollectionManager;

/**
 * Класс команды head
 */
public class HeadCommand extends Command {
    private final ConsoleOutput consoleOutput;
    private final CollectionManager collectionManager;

    public HeadCommand(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("head", "выводит первый элемент коллекции");
        this.consoleOutput = consoleOutput;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 0) {
            consoleOutput.printError("Команда не принимает аргументов!!");
            return;
        }
        if (collectionManager.getCollectionSize() == 0) {
            consoleOutput.printError("Коллекция пустая");
            return;
        }
        consoleOutput.println(CollectionManager.getCollection().peek().toString());

    }
}
