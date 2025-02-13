package org.example.command.commands;

import org.example.command.Command;
import org.example.command.ConsoleOutput;
import org.example.entity.Ticket;
import org.example.managers.CollectionManager;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Класс команды filter_starts_with_name
 */
public class FilterStartsWithNameCommand extends Command {
    private final ConsoleOutput consoleOutput;
    private final CollectionManager collectionManager;

    public FilterStartsWithNameCommand(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("filter_starts_with_name", "вывести элементы, значение поля name которых начинается с заданной подстроки");
        this.consoleOutput = consoleOutput;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            consoleOutput.printError("Команда принимает 1 аргумент!!");
            return;
        }
        PriorityQueue<Ticket> collection = CollectionManager.getCollection();
        List<Ticket> listOfTickets = collection.stream().filter((ticket -> ticket.getName().startsWith(args[0]))).toList();
        collection = new PriorityQueue<>(listOfTickets);
        if (collection.size() == 0) {
            consoleOutput.println("Не найдено билетов, название которых начинается на \"" + args[0] + "\"");
            return;
        }
        consoleOutput.println("Найдено " + collection.size() + ":");
        int i = 1;
        while(!collection.isEmpty()) {
            consoleOutput.println("(" + i++ + ") " + collection.poll());
        }
    }
}
