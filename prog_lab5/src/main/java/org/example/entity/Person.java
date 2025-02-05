package org.example.entity;

import org.example.utils.Validatable;

public class Person implements Validatable{
    private long height; //Значение поля должно быть больше 0
    private Country nationality; //Поле может быть null

    @Override
    public boolean validate() {
        if (height <= 0) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Person {" + "\n" +
                "height = " + this.height + ",\n" +
                "nationality = " + this.nationality + "\n" +
                "}";
    }

}
