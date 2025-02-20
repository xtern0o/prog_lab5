package org.example;

import org.example.command.*;
import org.example.command.commands.*;
import org.example.managers.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static ConsoleOutput consoleOutput = new ConsoleOutput();
    static ConsoleInput consoleInput = new ConsoleInput();
    static CollectionManager collectionManager = new CollectionManager();
    static CommandManager commandManager = new CommandManager();
    static RunnableScriptsManager runnableScriptsManager = new RunnableScriptsManager();

    public static void main(String[] args) {
        if (!validateArgs(args)) return;

        FileManager fileManager = new FileManager(new File(args[0]), consoleOutput);

        if (!fileManager.validate()) return;

        fileManager.deserializeCollectionFromJSON();

        ArrayList<Command> commands = new ArrayList<>(Arrays.asList(
                new HelpCommand(commandManager, consoleOutput),
                new HistoryCommand(commandManager, consoleOutput),
                new AddCommand(consoleOutput, consoleInput, collectionManager),
                new ShowCommand(consoleOutput),
                new InfoCommand(consoleOutput, collectionManager),
                new ClearCommand(consoleOutput, collectionManager),
                new ExitCommand(consoleOutput),
                new UpdateCommand(consoleInput, consoleOutput, collectionManager),
                new RemoveByIdCommand(consoleOutput, collectionManager),
                new HeadCommand(consoleOutput, collectionManager),
                new RemoveHeadCommand(consoleOutput, collectionManager),
                new FilterStartsWithNameCommand(consoleOutput, collectionManager),
                new PrintUniqueDiscountCommand(consoleOutput),
                new PrintFieldDescendingPersonCommand(consoleOutput),
                new SaveCommand(consoleOutput, fileManager),
                new ExecuteScriptCommand(consoleOutput, commandManager, consoleInput, runnableScriptsManager)
            )
        );
        commandManager.addCommands(commands);

        RuntimeManager runtimeManager = new RuntimeManager(consoleOutput, consoleInput, commandManager, fileManager);
        runtimeManager.run();
    }

    public static boolean validateArgs(String[] args) {
        if (args.length == 0) {
            consoleOutput.printError("Вы не ввели путь файла.\nКорректный запуск программы: java -jar prog_lab5-1.0-jar-with-dependencies.jar <файл с данными>.json\nДо свидания! :)");
            return false;
        }
        else if (args.length > 1) {
            consoleOutput.printError("Программа принимает 1 аргумент. До свидания! :)");
            return false;
        }
        return true;
    }
}


