package org.example.command.commands;

import org.example.command.Command;
import org.example.command.ConsoleOutput;
import org.example.managers.CollectionManager;

public class RemoveHeadCommand extends Command {
    private final ConsoleOutput consoleOutput;
    private final CollectionManager collectionManager;

    public RemoveHeadCommand(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("remove_head", "выводит первый элемент коллекции и удаляет его");
        this.consoleOutput = consoleOutput;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 0) {
            consoleOutput.println("Команда не принимает аргументов!!");
            return;
        }
        if (collectionManager.getCollectionSize() == 0) {
            consoleOutput.printError("Коллекция пустая");
            return;
        }
        consoleOutput.println("Эта запись была удалена:");
        consoleOutput.println(CollectionManager.getCollection().poll().toString());
    }
}
