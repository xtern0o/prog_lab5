package org.example.command.commands;

import org.example.command.Command;
import org.example.command.ConsoleOutput;
import org.example.entity.Ticket;
import org.example.managers.CollectionManager;

import java.util.List;

/**
 * Класс команды print_unique_discount_command
 */
public class PrintUniqueDiscountCommand extends Command {
    private final ConsoleOutput consoleOutput;

    public PrintUniqueDiscountCommand(ConsoleOutput consoleOutput) {
        super("print_unique_discount", "вывести элементы, значение поля name которых начинается с заданной подстроки");
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
