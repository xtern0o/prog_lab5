package org.example.managers;

import org.example.entity.Ticket;

import java.util.*;

public class CollectionManager {
    /**
     * Коллекция билетов
     */
    private static final PriorityQueue<Ticket> collection = new PriorityQueue<>();

    /**
     * Время инициализации коллекции
     * Время инициализации объекта CollectionManager
     */
    private final Date initDate = new Date();

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

    /**
     * Находит объект в коллекции по его id
     * @param id айди.
     * @return Объект из коллекции или null, если его не существует
     */
    public Ticket getElementById(Integer id) {
        for (Ticket ticket : collection) {
            if (Objects.equals(ticket.getId(), id)) return ticket;
        }
        return null;
    }

    /**
     * Статический метод для генерации нового id
     * @return минимальный несуществующий id
     */
    public static int generateFreeId() {
        if (collection.isEmpty()) return 1;

        HashSet<Integer> existIds = new HashSet<>();
        for (Ticket ticket : collection) {
            existIds.add(ticket.getId());
        }

        for (int i = 0; i < Collections.max(existIds); i++) {
            if (!existIds.contains(i)) return i;
        }
        return Collections.max(existIds) + 1;
    }

}
