package org.example.command.commands;

import org.example.command.Command;
import org.example.command.ConsoleOutput;

public class ExitCommand extends Command {
    private final ConsoleOutput consoleOutput;

    public ExitCommand(ConsoleOutput consoleInput) {
        super("exit", "выход без сохранения");
        this.consoleOutput = consoleInput;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 0) {
            consoleOutput.printError("Команда не принимает аргументов!!");
            return;
        }
        consoleOutput.println("Выход из системы. До свидания!!!");
        System.exit(0);
    }
}
