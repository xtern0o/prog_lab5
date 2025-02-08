package org.example.managers;

import lombok.Getter;
import org.example.entity.Ticket;
import org.example.utils.exceptions.ValidationError;

import java.util.*;

@Getter
public class CollectionManager {
    /**
     * Коллекция билетов
     */
    @Getter
    private static final PriorityQueue<Ticket> collection = new PriorityQueue<>();

    /**
     * Время инициализации коллекции
     * Время инициализации объекта CollectionManager
     */
    private final Date initDate = new Date();

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

        for (int i = 1; i < Collections.max(existIds); i++) {
            if (!existIds.contains(i)) return i;
        }
        return Collections.max(existIds) + 1;
    }

    /**
     * Получение типа коллекции
     * @return класс объекта коллекции
     */
    public String getTypeOfCollection() {
        return collection.getClass().getName();
    }

    /**
     * Возвращает размер коллекции
     * @return число элементов в коллекции
     */
    public int getCollectionSize() {
        return collection.size();
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
     * Добавляет элемент в коллекцию предварительно проведя контрольную валидацию
     * @param ticket новый элемент
     * @throws ValidationError в случае неудачного прохождения валидации
     */
    public void addElement(Ticket ticket) throws ValidationError {
        if (ticket.validate()) {
            collection.add(ticket);
            return;
        }
        throw new ValidationError(ticket);
    }

}
