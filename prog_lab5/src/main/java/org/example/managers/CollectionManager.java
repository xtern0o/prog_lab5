package org.example.managers;

import org.example.entity.Ticket;

import java.util.PriorityQueue;

public class CollectionManager {
    /**
     * Коллекция билетов
     */
    private static final PriorityQueue<Ticket> collection = new PriorityQueue<>();

    /**
     * Возвращает коллекцию
     * @return коллекция
     */
    public PriorityQueue<Ticket> getCollection() {
        return collection;
    }

    /**
     * Добавление нового элемента в коллекцию
     * @param t новый билет
     */
    public void addNewElement(Ticket t) {
        collection.add(t);
    }

}
