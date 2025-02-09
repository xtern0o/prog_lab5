package org.example.command.commands;

import org.example.command.Command;
import org.example.command.ConsoleOutput;
import org.example.entity.Ticket;
import org.example.managers.CollectionManager;

import java.util.List;
import java.util.PriorityQueue;

public class PrintUniqueDiscountCommand extends Command {
    private final ConsoleOutput consoleOutput;
    private final CollectionManager collectionManager;

    public PrintUniqueDiscountCommand(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("print_unique_discount", "вывести элементы, значение поля name которых начинается с заданной подстроки");
        this.collectionManager = collectionManager;
        this.consoleOutput = consoleOutput;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 0) {
            consoleOutput.printError("Команда не принимает аргументов");
            return;
        }

        List<Float> uniqueDiscounts = CollectionManager.getCollection()
                .stream()
                .map(Ticket::getDiscount)
                .distinct()
                .toList();
        if (uniqueDiscounts.isEmpty()) {
            consoleOutput.println("Похоже, коллекция пуста!!");
            return;
        }
        consoleOutput.println("Уникальных значений discount: " + uniqueDiscounts.size());
        for (Float d : uniqueDiscounts) {
            consoleOutput.println(": " + d);
        }
    }
}
