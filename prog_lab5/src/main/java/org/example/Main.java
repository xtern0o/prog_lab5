package org.example;

import org.example.command.Command;
import org.example.command.ConsoleInput;
import org.example.command.ConsoleOutput;
import org.example.command.commands.HelpCommand;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.managers.FileManager;
import org.example.managers.RuntimeManager;

import java.io.File;
import java.util.ArrayList;

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

        commandManager.addCommand(new HelpCommand(commandManager, consoleOutput));


        RuntimeManager runtimeManager = new RuntimeManager(consoleOutput, consoleInput, commandManager, fileManager);
        runtimeManager.run();
    }
}


