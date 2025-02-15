package org.example.command;

import org.example.managers.InputManager;
import org.example.utils.InputReader;

import java.util.Scanner;

/**
 * Класс для контроля пользовательского ввода
 */
public class ConsoleInput implements InputReader {
    private static final Scanner scanner = InputManager.scanner;

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
