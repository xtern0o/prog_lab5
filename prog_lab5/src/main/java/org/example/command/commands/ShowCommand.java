package org.example.command.commands;

import lombok.AllArgsConstructor;
import org.example.command.Command;
import org.example.entity.Ticket;
import org.example.managers.CollectionManager;
import org.example.utils.Printable;

import java.util.Collection;
import java.util.PriorityQueue;

public class ShowCommand extends Command {
    private final Printable consoleOutput;
    private final CollectionManager collectionManager;

    public ShowCommand(Printable consoleOutput, CollectionManager collectionManager) {
        super("show", "выводит в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.consoleOutput = consoleOutput;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        // копируем чтобы удалить элементы в порядке приоритета
        PriorityQueue<Ticket> collection = new PriorityQueue<>(collectionManager.getCollection());
        if (collection.isEmpty()) {
            consoleOutput.println("Коллекция пуста :( Добавьте ченить");
            return;
        }
        consoleOutput.println("Всего элементов в коллекции: " + + collection.size() + ".\nЭлементы коллекции в порядке возростания приоритета:");
        int i = 1;
        while (!collection.isEmpty()) {
            consoleOutput.println(
                    "(" + i++ + ") " + collection.poll()
            );
        }
    }
}
