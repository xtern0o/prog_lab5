package org.example.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * Абстрактный класс для всех команд
 * @author maxkarn
 */
@AllArgsConstructor
@Getter
public abstract class Command implements CommandInterface {
    private String name;
    private String description;

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return name + " : " + description;
    }

}
