package org.example.entity.builders;

import org.example.command.ConsoleInput;
import org.example.utils.Printable;

import java.util.function.Predicate;

/**
 * Базовый абстрактный класс Builder
 * Предназначен для создания сложных объектов поэтапно
 * @param <T>
 */
public abstract class Builder<T> {
    private final Printable consoleOutput;
    private final ConsoleInput consoleInput;

    public Builder(Printable consoleOutput, ConsoleInput consoleInput) {
        this.consoleOutput = consoleOutput;
        this.consoleInput = consoleInput;
    }

    /**
     * Построение исходного объекта
     * @return объект, построенный на основе полученных данных
     */
    public abstract T build();

    /**
     * Получение строкового значения. До тех пор, пока не получится валидное.
     * Для дальнейших аналогичных типов данных сигнатура будет той же, поэтому описание параметров я напишу только в этом методе
     * @param valueName название поля для заполнения
     * @param valueInfo дополнительная информация, требования для поля и тд
     * @param validateRule предикат (лямбда), описывающий правило валидации значения
     * @param errorMessage сообщение об ошибке, которое получит пользователь при провале валидации
     * @return полученная валидная строка
     */
    public String askString(String valueName, String valueInfo, Predicate<String> validateRule, String errorMessage) {
        while (true) {
            consoleOutput.print(String.format("-> %s (%s): ", valueName, valueInfo));
            String value = consoleInput.readLine().trim();
            if (validateRule.test(value)) return value;
            consoleOutput.printError("Введенное значение не удовлетворяет одному или нескольким условиям валидации поля \"" + valueName + "\". " + errorMessage);
        }
    }

    public Integer askInteger(String valueName, String valueInfo, Predicate<Integer> validateRule, String errorMessage) {
        while (true) {
            consoleOutput.print(String.format("-> %s (%s): ", valueName, valueInfo));
            try {
                Integer value = Integer.parseInt(consoleInput.readLine());
                if (validateRule.test(value)) return value;
                consoleOutput.printError("Введенное значение не удовлетворяет одному или нескольким условиям валидации поля \"" + valueName + "\". " + errorMessage);
            }
            catch (NumberFormatException e) {
                consoleOutput.printError("Что непонятного?! Введите число!!");
            }
        }
    }

}
