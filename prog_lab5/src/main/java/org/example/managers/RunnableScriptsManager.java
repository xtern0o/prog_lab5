package org.example.managers;

import org.example.command.ConsoleInput;
import org.example.utils.InputReader;

import java.io.*;
import java.util.ArrayDeque;

/**
 * Менеджер для контроля корректности выполнения исполняемых скриптов
 * @author maxkarn
 */
public class RunnableScriptsManager implements InputReader {
    /**
     * Хранение запущенных на данный моент файлов
     */
    private static final ArrayDeque<File> launchedFiles = new ArrayDeque<>();

    private static final ArrayDeque<BufferedReader> readers = new ArrayDeque<>();

    /**
     * Метод для проверки, был ли запущен файл повторно (рекурсивно)
     * @param file проверяемый файл
     * @return был или не был запущен
     */
    public static boolean checkIfLaunchedInStack(File file) {
        return launchedFiles.contains(file);
    }

    /**
     * Добавляет файл в список запущенных
     * @param file файл
     */
    public static void addFile(File file) throws FileNotFoundException {
        launchedFiles.add(file);
        readers.add(new BufferedReader(new InputStreamReader(new FileInputStream(file))));
    }

    /**
     * Удаляет файл из списка запущенных
     * @param file файл
     */
    public static void removeFile(File file) {
        launchedFiles.remove(file);
    }

    /**
     * Метод для чтения перенаправленного потока ввода на файл
     * @throws IOException если файл не найден (гарантируется наличие)
     */
    @Override
    public String readLine() {
        try {
            return readers.getLast().readLine();
        } catch (IOException ignored) {
            return "";
        }
    }
}
