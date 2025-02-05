package org.example.entity;

import org.example.utils.Validatable;

public class Coordinates implements Validatable {
    private float x;
    private Integer y; //Значение поля должно быть больше -471, Поле не может быть null

    public Coordinates(float x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean validate() {
        if (y == null || y <= -471) return false;
        return true;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
