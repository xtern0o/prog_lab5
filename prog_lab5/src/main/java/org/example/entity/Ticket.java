package org.example.entity;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.example.utils.Validatable;

public class Ticket implements Validatable, Comparable<Ticket> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double price; //Значение поля должно быть больше 0
    private Float discount; //Поле не может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 100
    private boolean refundable;
    private TicketType type; //Поле может быть null
    private Person person; //Поле не может быть null

    @Override
    public boolean validate() {
        if (id == null || id <= 0) return false;
        // TODO: проверять уникальность id
        if (name == null || name.isEmpty()) return false;
        if (creationDate == null) return false;
        if (price <= 0) return false;
        if (discount == null || discount <= 0 || discount > 100) return false;
        if (person == null) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Ticket {" + "\n" +
                "id = " + this.id + ",\n" +
                "name = " + this.name + ",\n" +
                "coordinates = " + this.coordinates + ",\n" +
                "creationDate = " + this.creationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + ",\n" +
                "price = " + this.price + ",\n" +
                "discount = " + this.discount + ",\n" +
                "refundable = " + this.refundable + ",\n" +
                "type = " + this.type + ",\n" +
                "person = " + this.person + "\n" +
                "}";
    }

    @Override
    public int compareTo(Ticket ticket) {
        // TODO: сделать нормальный compareTo
        return Integer.compare(this.id, ticket.id);
    }
}