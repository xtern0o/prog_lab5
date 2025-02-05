package org.example.managers;

import org.example.command.ConsoleOutput;
import org.example.utils.Validatable;

import java.io.File;

/**
 * Менеджер файлов.
 * Служит для управления файлами
 * @author maxkarn
 */
public class FileManager implements Validatable {
    private final String filePath;
    private final ConsoleOutput consoleOutput;
    private final CollectionManager collectionManager;

    public FileManager(String filePath, ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        this.filePath = filePath;
        this.consoleOutput = consoleOutput;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean validate() {
        File dataFile = new File(filePath);
        if (!dataFile.exists()) {
            consoleOutput.printError("Файла, введенного в качестве аргумента выполнения программы не сузествует. До свидания! :)");
            return false;
        }
        if (!dataFile.canRead() || !dataFile.canWrite()) {
            consoleOutput.printError("Недостаточно прав: файл недоступен для чтения и(или) для записи, программа может работать некорректно. До свидания! :)");
            return false;
        }
        return true;
    }
}
