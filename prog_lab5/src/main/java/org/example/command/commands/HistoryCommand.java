package org.example.command.commands;

import org.example.command.Command;
import org.example.command.ConsoleOutput;
import org.example.managers.CommandManager;

import java.util.ArrayList;

public class HistoryCommand extends Command {
    private CommandManager commandManager;
    private ConsoleOutput consoleOutput;

    public HistoryCommand(CommandManager commandManager, ConsoleOutput consoleOutput) {
        super("history", "Выводит названия пяти последних выполненных команд");
        this.commandManager = commandManager;
        this.consoleOutput = consoleOutput;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            ArrayList<Command> history = commandManager.getHistory();
            if (history.size() > 5) {
                history = new ArrayList<>(history.subList(history.size() - 5, history.size()));
            }
            if (history.size() == 0) {
                consoleOutput.println("Похоже, до этого вы не выполнили ни одной команды!!!");
                commandManager.addToHistory(this);
                return;
            }
            consoleOutput.println(String.format("Эти команды были выполнены в последнее время (%d):", history.size()));
            for (int i = 1; i <= history.size(); i++) {
                consoleOutput.println(i + ". " + history.get(i - 1).getName());
            }
            commandManager.addToHistory(this);
            return;
        }
        consoleOutput.printError("Эта команда не принимает никаких аргументов.");
    }
}
