package org.example;

import org.example.command.Command;
import org.example.command.ConsoleInput;
import org.example.command.ConsoleOutput;
import org.example.command.commands.AddCommand;
import org.example.command.commands.HelpCommand;
import org.example.command.commands.HistoryCommand;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.managers.FileManager;
import org.example.managers.RuntimeManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ConsoleOutput consoleOutput = new ConsoleOutput();
        ConsoleInput consoleInput = new ConsoleInput();
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager();

        if (args.length == 0) {
            consoleOutput.printError("Вы не ввели путь файла. До свидания! :)");
            return;
        }
        else if (args.length > 1) {
            consoleOutput.printError("Программа принимает 1 аргумент. До свидания! :)");
            return;
        }

        FileManager fileManager = new FileManager(args[0], consoleOutput, collectionManager);
        if (!fileManager.validate()) return;

        ArrayList<Command> commands = new ArrayList<>(Arrays.asList(
                new HelpCommand(commandManager, consoleOutput),
                new HistoryCommand(commandManager, consoleOutput),
                new AddCommand(consoleOutput, consoleInput, collectionManager)));
        commandManager.addCommands(commands);


        RuntimeManager runtimeManager = new RuntimeManager(consoleOutput, consoleInput, commandManager, fileManager);
        runtimeManager.run();
    }

    public static boolean argsValidator(String[] args) {

    }
}


