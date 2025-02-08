package org.example.command.commands;

import org.example.command.Command;
import org.example.command.ConsoleOutput;
import org.example.managers.CommandManager;


public class HelpCommand extends Command {
    private CommandManager commandManager;
    private ConsoleOutput consoleOutput;

    public HelpCommand(CommandManager commandManager, ConsoleOutput consoleOutput) {
        super("help", "Вывод справки о доступных командах");
        this.commandManager = commandManager;
        this.consoleOutput = consoleOutput;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            consoleOutput.println("Краткая справка по всем доступным командам.");
            commandManager.getCommands().forEach(((commandName, command) -> consoleOutput.println(command.toString())));
            return;
        }
        consoleOutput.printError("Эта команда не принимает никаких аргументов.");
    }
}
