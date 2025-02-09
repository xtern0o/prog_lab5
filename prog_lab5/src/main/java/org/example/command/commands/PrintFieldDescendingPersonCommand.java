package org.example.command.commands;

import org.example.command.Command;
import org.example.command.ConsoleOutput;
import org.example.entity.Ticket;
import org.example.managers.CollectionManager;

import java.util.PriorityQueue;

public class PrintFieldDescendingPersonCommand extends Command {
    private final ConsoleOutput consoleOutput;

    public PrintFieldDescendingPersonCommand(ConsoleOutput consoleOutput) {
        super("print_field_descending_person", "вывести значения поля person всех элементов в порядке убывания");
        this.consoleOutput = consoleOutput;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 0) {
            consoleOutput.printError("Команда не принимает аргументов!!");
            return;
        }
        PriorityQueue<Ticket> collectionCopy = new PriorityQueue<>(CollectionManager.getCollection());
        if (collectionCopy.isEmpty()) {
            consoleOutput.println("Похоже, коллекция пуста!!");
            return;
        }
        consoleOutput.println("Поля person элементов коллекции в порядке убывания приоритета:");
        int i = 1;
        while (!collectionCopy.isEmpty()) {
            consoleOutput.println(i++ + " " + collectionCopy.poll().getPerson());
        }
    }
}
