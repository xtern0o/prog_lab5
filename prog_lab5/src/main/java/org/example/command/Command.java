package org.example.command;

import java.util.Objects;

/**
 * Абстрактный класс для всех команд
 * @author maxkarn
 */
public abstract class Command implements CommandInterface {
    private String name;
    private String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return name + " : " + description;
    }

}
