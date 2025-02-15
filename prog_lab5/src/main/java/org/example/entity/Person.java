package org.example.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.utils.Validatable;

/**
 * Модель Person
 * @author maxkarn
 */
@Getter
@Setter
public class Person implements Validatable{
    private long height; //Значение поля должно быть больше 0
    private Country nationality; //Поле может быть null

    @JsonCreator
    public Person(@JsonProperty("height") long height, @JsonProperty("nationality") Country nationality) {
        this.height = height;
        this.nationality = nationality;
    }

    @Override
    public boolean validate() {
        return height > 0;
    }

    @Override
    public String toString() {
        return String.format("Person (height: %s; nationality: %s)", height, nationality != null ? nationality : "?");
    }

}
