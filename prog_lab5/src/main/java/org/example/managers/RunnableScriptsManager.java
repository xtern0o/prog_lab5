package org.example.managers;

import java.io.File;
import java.util.HashSet;

/**
 * Менеджер для контроля корректности выполнения исполняемых скриптов
 * @author maxkarn
 */
public class RunnableScriptsManager {
    /**
     * Хранение запущенных на данный моент файлов
     * (можно было сделать дек или стек, но тут достаточно хэшсета)
     */
    private static final HashSet<File> launchedFiles = new HashSet<>();

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
    public static void addFile(File file) {
        launchedFiles.add(file);
    }

    /**
     * Удаляет файл из списка запущенных
     * @param file файл
     */
    public static void removeFile(File file) {
        launchedFiles.remove(file);
    }

}
