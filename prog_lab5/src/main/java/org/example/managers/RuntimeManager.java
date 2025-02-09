package org.example.managers;

import lombok.AllArgsConstructor;
import org.example.utils.InputReader;
import org.example.utils.Printable;

import java.util.Arrays;
import java.util.NoSuchElementException;

@AllArgsConstructor
public class RuntimeManager implements Runnable {
    private final Printable consoleOutput;
    private final InputReader consoleInput;
    private final CommandManager commandManager;
    private final FileManager fileManager;

    @Override
    public void run() {
        // hook, срабатывающий при завершении программы
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            consoleOutput.println("Завершение работы программы. До свидания!!");
        }));

        consoleOutput.println(
                "ghbdtn! Вы используете программу prog_lab5 версии 1.0 в режиме треминала!!! \n" +
                        "Для справки по доступным командам воспользуйтесь командой \"help\"");
        consoleOutput.println("(*) Взаимодействие с файлом: " + fileManager.getFile().getName());
        while (true) {
            try {
                consoleOutput.print("$ ");
                String query = consoleInput.readLine().trim();
                String[] queryParts = query.split(" ");
                launchCommand(queryParts);
            } catch (NoSuchElementException e) {  // ^D
                consoleOutput.println("Конец ввода.");
                break;
            } catch (Exception e) {
                consoleOutput.printError("Ошибка во время выполнения: " + e.getMessage());
                break;
            }
        }
    }

    public void launchCommand(String[] queryParts) {
        String qCommandName = queryParts[0];
        if (qCommandName.isBlank()) return;
        String[] qCommandArgs = Arrays.copyOfRange(queryParts, 1, queryParts.length);
        if (!commandManager.getCommands().containsKey(qCommandName)) {
            consoleOutput.printError(String.format("Команда \"%s\" не найдена. Воспользуйтесь командой \"help\" для просмотра доступных команд", qCommandName));
            return;
        }
        try {
            commandManager.getCommands().get(qCommandName).execute(qCommandArgs);
            commandManager.addToHistory(commandManager.getCommands().get(qCommandName));
        } catch (NoSuchElementException e) {
            // Ctrl + D
        } catch (Exception e) {
            consoleOutput.printError("Произошла ошибка во время выполнения команды \"" + qCommandName + "\": " + e.getMessage());
        }

    }
}
