package org.example.managers;

import lombok.Getter;
import org.example.command.ConsoleOutput;
import org.example.utils.Validatable;

import java.io.File;
import java.util.Objects;

/**
 * Менеджер файлов.
 * Служит для управления файлами
 * @author maxkarn
 */
public class FileManager implements Validatable {
    @Getter
    private final File file;
    private final ConsoleOutput consoleOutput;
    private final CollectionManager collectionManager;

    public FileManager(File file, ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        this.file = file;
        this.consoleOutput = consoleOutput;
        this.collectionManager = collectionManager;
    }

    /**
     * Статическая функция для получения расширения файла
     * @param file файл
     * @return расширение файла или null если он не имеет расширения
     */
    public static String getFileFormat(File file) {
        String name = file.getName();
        if (!name.contains(".")) return null;
        return name.substring(name.lastIndexOf('.') + 1);
    }

    @Override
    public boolean validate() {
        if (!file.exists()) {
            consoleOutput.printError("Файла, введенного в качестве аргумента выполнения программы не сузествует. До свидания! :)");
            return false;
        }
        if (!file.canRead() || !file.canWrite()) {
            consoleOutput.printError("Недостаточно прав: файл недоступен для чтения и(или) для записи, программа может работать некорректно. До свидания! :)");
            return false;
        }
        if (!Objects.equals(getFileFormat(file), "json")) {
            consoleOutput.printError("Программа работает только с файлами json. Выберите корректный файл");
            return false;
        }
        return true;
    }

}
