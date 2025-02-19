package org.example.entity.builders;

import org.example.command.ConsoleInput;
import org.example.entity.Coordinates;
import org.example.utils.InputReader;
import org.example.utils.Printable;

import java.util.function.Predicate;

/**
 * Класс билдера для объектов класса Coordinates
 */
public class CoordinatesBuilder extends Builder<Coordinates> {
    public CoordinatesBuilder(Printable consoleOutput, InputReader consoleInput) {
        super(consoleOutput, consoleInput);
    }

    @Override
    public Coordinates build() {
        Predicate<Float> validateX = (x) -> (true);
        Predicate<Integer> validateY = (y) -> (y != null && y > -471);

        consoleOutput.println("Создание нового объекта Coordinates");

        Float x = askFloat("координата x", "дробное число типа Float", validateX, "Неверный формат ввода!!");
        Integer y = askInteger("координата y", "целое число; значение не пусто; значение больше -471", validateY, "Неверный формат ввода: число должно удовлетворять требованиям");

        return new Coordinates(x, y);
    }
}
