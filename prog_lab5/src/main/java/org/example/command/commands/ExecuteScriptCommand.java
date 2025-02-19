package org.example.command.commands;

import org.example.command.Command;
import org.example.command.ConsoleInput;
import org.example.command.ConsoleOutput;
import org.example.managers.CommandManager;
import org.example.managers.InputManager;
import org.example.managers.RunnableScriptsManager;
import org.example.managers.RuntimeManager;

import java.io.*;

/**
 * Класс команды execute_script
 */
public class ExecuteScriptCommand extends Command {
    private final ConsoleOutput consoleOutput;
    private final CommandManager commandManager;
    private final ConsoleInput consoleInput;
    private final RunnableScriptsManager runnableScriptsManager;

    public ExecuteScriptCommand(ConsoleOutput consoleOutput, CommandManager commandManager, ConsoleInput consoleInput, RunnableScriptsManager runnableScriptsManager) {
        super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        this.consoleOutput = consoleOutput;
        this.commandManager = commandManager;
        this.consoleInput = consoleInput;
        this.runnableScriptsManager = runnableScriptsManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            consoleOutput.printError("Команда принимает 1 аргумент!!");
            return;
        }
        try {
            File scriptFile = new File(args[0]);
            if (!scriptFile.exists()) {
                throw new FileNotFoundException();
            }

            consoleOutput.println(String.format("(*) Исполнение файла \"%s\":", scriptFile.getName()));

            if (RunnableScriptsManager.checkIfLaunchedInStack(scriptFile)) {
                consoleOutput.printError(String.format("Файл \"%s\" вызывается рекурсивно. Исправьте код исполняемых файлов!!", scriptFile.getName()));
                return;
            }


            ConsoleInput.setFileMode(true);
            RunnableScriptsManager.addFile(scriptFile);

            for (String line = runnableScriptsManager.readLine(); line != null; line = runnableScriptsManager.readLine()) {
                RuntimeManager.launchCommand(line.split(" "), commandManager, consoleOutput);
            }

            consoleOutput.println("(*) Завершение исполнения файла " + scriptFile.getName());

            RunnableScriptsManager.removeFile(scriptFile);

            ConsoleInput.setFileMode(false);

        } catch (FileNotFoundException e) {
            consoleOutput.printError("Файл с программой \"" + args[0] + "\" не найден");
        }
    }
}
