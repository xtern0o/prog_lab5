package org.example.managers;

import org.example.command.Command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Менеджер для управления доступными командами
 * @author maxkarn
 */
public class CommandManager {
    private final HashMap<String, Command> commands = new HashMap<>();
    private final ArrayList<Command> history = new ArrayList<>();

    /**
     * Добавляет команды в коллекцию команд
     * @param command объект команды
     */
    public void addCommand(Command command) {
        this.commands.put(command.getName(), command);
    }

    /**
     * Добавляет коллекцию команд в колеекцию команд
     * @param commandCollection коллекция из добавляемых команд
     */
    public void addCommands(Collection<Command> commandCollection) {
        for (Command command : commandCollection) {
            addCommand(command);
        }
    }

    /**
     * Сохраняет исполненную команду в истории
     * @param command команда, сохраняемая в истории
     */
    public void addToHistory(Command command) {
        history.add(command);
    }

    /**
     * Получение списка доступных команд
     * @return HashMap{commandName, command}
     */
    public HashMap<String, Command> getCommands() {
        return commands;
    }

    /**
     * Получение истории команд в текущей сессии
     * @return Использованные команды ArrayList<Command>
     */
    public ArrayList<Command> getHistory() {
        return history;
    }

    /**
     * Производит выполнение команды
     * @param name имя команды
     * @param args аргументы необходимые для выполнения команды
     */
    public void execute(String name, String[] args) {
        Command command = commands.get(name);
        command.execute(args);
    }
}
