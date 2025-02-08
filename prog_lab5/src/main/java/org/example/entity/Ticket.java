package org.example.entity;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;
import org.example.managers.CollectionManager;
import org.example.utils.Validatable;

@Getter
public class Ticket implements Validatable, Comparable<Ticket> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @Setter
    private String name; //Поле не может быть null, Строка не может быть пустой

    @Setter
    private Coordinates coordinates; //Поле не может быть null

    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Setter
    private double price; //Значение поля должно быть больше 0

    @Setter
    private Float discount; //Поле не может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 100

    @Setter
    private boolean refundable;

    @Setter
    private TicketType type; //Поле может быть null

    @Setter
    private Person person; //Поле не может быть null

    public Ticket(String name, Coordinates coordinates, double price, Float discount, TicketType type, boolean refundable, Person person) {
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.discount = discount;
        this.type = type;
        this.refundable = refundable;
        this.person = person;

        this.id = CollectionManager.generateFreeId();
        this.creationDate = ZonedDateTime.now();
    }

    @Override
    public boolean validate() {
        if (id == null || id <= 0) return false;
        if (name == null || name.isEmpty()) return false;
        if (creationDate == null) return false;
        if (price <= 0) return false;
        if (discount == null || discount <= 0 || discount > 100) return false;
        if (person == null) return false;

        return person.validate() && coordinates.validate();
    }

//    @Override
//    public String toString() {
//        return "Ticket(" +
//                "id = " + this.id + ", " +
//                "name = " + this.name + ", " +
//                "coordinates = " + this.coordinates + ", " +
//                "creationDate = " + this.creationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + ", " +
//                "price = " + this.price + ", " +
//                "discount = " + this.discount + ", " +
//                "refundable = " + this.refundable + ", " +
//                "type = " + this.type + ", " +
//                "person = " + this.person + ")";
//    }

    @Override
    public String toString() {
        return String.format(
                "Ticket:\n" +
                "-------------+-----------------------------------------------------" + "\n" +
                "id           | %-50d |\n" +
                "name         | %-50s |\n" +
                "coordinates  | %-50s |\n" +
                "creationDate | %-50s |\n" +
                "price        | %-50.2f |\n" +
                "discount     | %-50.2f |\n" +
                "refundable   | %-50s |\n" +
                "type         | %-50s |\n" +
                "person       | %-50s |\n" +
                "-------------+-----------------------------------------------------",
                id,
                name,
                coordinates,
                this.creationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                price,
                discount,
                refundable ? "Yes" : "No",
                type != null ? type.name() : "?",
                person
        );
    }

    @Override
    public int compareTo(Ticket ticket) {
        // TODO: сделать нормальный compareTo
        return Integer.compare(this.id, ticket.id);
    }
}