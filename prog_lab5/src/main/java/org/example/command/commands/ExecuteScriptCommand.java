package org.example.command.commands;

import org.example.command.Command;
import org.example.command.ConsoleOutput;
import org.example.managers.CommandManager;
import org.example.managers.RunnableScriptsManager;
import org.example.managers.RuntimeManager;

import java.io.*;

/**
 * Класс команды execute_script
 */
public class ExecuteScriptCommand extends Command {
    private final ConsoleOutput consoleOutput;
    private final CommandManager commandManager;

    public ExecuteScriptCommand(ConsoleOutput consoleOutput, CommandManager commandManager) {
        super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        this.consoleOutput = consoleOutput;
        this.commandManager = commandManager;
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

            RunnableScriptsManager.addFile(scriptFile);

            FileInputStream fileInputStream = new FileInputStream(scriptFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                RuntimeManager.launchCommand(line.split(" "), commandManager, consoleOutput);
                line = bufferedReader.readLine();
            }

            consoleOutput.println("(*) Завершение исполнения файла " + scriptFile.getName());

            RunnableScriptsManager.removeFile(scriptFile);

        } catch (FileNotFoundException e) {
            consoleOutput.printError("Файл с программой \"" + args[0] + "\" не найден");
        } catch (IOException e) {
            consoleOutput.printError("Ошибка чтения файла " + args[0]);
        }
    }
}
