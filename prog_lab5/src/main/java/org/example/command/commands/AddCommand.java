package org.example.command.commands;

import org.example.command.Command;
import org.example.command.ConsoleInput;
import org.example.command.ConsoleOutput;
import org.example.entity.builders.TicketBuilder;
import org.example.managers.CollectionManager;

public class AddCommand extends Command {
    private ConsoleOutput consoleOutput;
    private ConsoleInput consoleInput;
    private CollectionManager collectionManager;

    public AddCommand(ConsoleOutput consoleOutput, ConsoleInput consoleInput, CollectionManager collectionManager) {
        super("add", "add {element} - добавить новый элемент в коллекцию");
        this.consoleOutput = consoleOutput;
        this.consoleInput = consoleInput;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        consoleOutput.println("Добавление нового объекта в коллекцию. Введите необходимые данные");
        collectionManager.addElement(new TicketBuilder(consoleOutput, consoleInput).build());
        consoleOutput.println("Объект успешно добавлени в коллекцию!");
    }
}
