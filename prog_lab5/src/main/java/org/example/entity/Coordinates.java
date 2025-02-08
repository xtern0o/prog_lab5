package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.Validatable;

@Getter
@Setter
public class Coordinates implements Validatable {
    private float x;
    private Integer y; //Значение поля должно быть больше -471, Поле не может быть null

    public Coordinates(float x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean validate() {
        return y != null && y > -471;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
