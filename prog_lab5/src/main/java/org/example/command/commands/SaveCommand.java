package org.example.command.commands;

import org.example.command.Command;
import org.example.command.ConsoleOutput;
import org.example.managers.CollectionManager;
import org.example.managers.FileManager;

import java.io.FileNotFoundException;

public class SaveCommand extends Command {
    private final ConsoleOutput consoleOutput;
    private final FileManager fileManager;

    public SaveCommand(ConsoleOutput consoleOutput, FileManager fileManager) {
        super("save", "сохранить коллекцию в файл");
        this.consoleOutput = consoleOutput;
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 0) {
            consoleOutput.println("Команда не принимает аргументов!!");
            return;
        }
        try {
            fileManager.serializeCollectionToJSON(CollectionManager.getCollection());
            if (CollectionManager.getCollection().isEmpty()) consoleOutput.println("Внимание: вы сохранили пустую коллекцию в файл " + fileManager.getFile().getName());
            else consoleOutput.println("Коллекция успешно сохранена в " + fileManager.getFile().getName());
        } catch (FileNotFoundException e) {
            // вообще программа гарантирует наличие файла
            consoleOutput.printError("Файл не найден. Зачем вы его удалили?? мдаа птщ не абаюдно");
            throw new RuntimeException(e);
        }
    }
}
