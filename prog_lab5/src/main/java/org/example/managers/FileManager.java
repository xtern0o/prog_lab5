package org.example.managers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import org.example.command.ConsoleOutput;
import org.example.entity.Ticket;
import org.example.utils.Validatable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * Менеджер файлов.
 * Служит для управления файлами
 * @author maxkarn
 */
public class FileManager implements Validatable {
    @Getter
    private final File file;
    private final ConsoleOutput consoleOutput;
    private final CollectionManager collectionManager;

    public FileManager(File file, ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        this.file = file;
        this.consoleOutput = consoleOutput;
        this.collectionManager = collectionManager;
    }

    /**
     * Статическая функция для получения расширения файла
     * @param file файл
     * @return расширение файла или null если он не имеет расширения
     */
    public static String getFileFormat(File file) {
        String name = file.getName();
        if (!name.contains(".")) return null;
        return name.substring(name.lastIndexOf('.') + 1);
    }

    @Override
    public boolean validate() {
        if (!file.exists()) {
            consoleOutput.printError("Файла, введенного в качестве аргумента выполнения программы не сузествует. До свидания! :)");
            return false;
        }
        if (!file.canRead() || !file.canWrite()) {
            consoleOutput.printError("Недостаточно прав: файл недоступен для чтения и(или) для записи, программа может работать некорректно. До свидания! :)");
            return false;
        }
        if (!Objects.equals(getFileFormat(file), "json")) {
            consoleOutput.printError("Программа работает только с файлами json. Выберите корректный файл");
            return false;
        }
        return true;
    }

    /**
     * сериализация коллекции в json с помощью PrintWriter
     * @param collection коллекция
     * @throws FileNotFoundException если файл не найден (программа гарантирует наличие файла)
     */
    public void serializeCollectionToJSON(Collection<Ticket> collection) throws FileNotFoundException {
        try (PrintWriter printWriter = new PrintWriter(file)) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            printWriter.print(objectMapper.writeValueAsString(collection));

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void deserializeCollectionFromJSON() {
        try (FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String json = "";
            String line = bufferedReader.readLine();
            while (line != null) {
                json += line;
                line = bufferedReader.readLine();
            }

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            PriorityQueue<Ticket> jsonCollection = objectMapper.readValue(
                    json,
                    objectMapper
                            .getTypeFactory()
                            .constructCollectionType(PriorityQueue.class, Ticket.class)
            );

            CollectionManager.setCollection(jsonCollection);

        } catch (IOException e) {
            consoleOutput.printError("Ну как вы умудрились получить эту ошибку...? Не надо удалять файл во время работы программы!!!");
            consoleOutput.printError(e.getMessage());
        }
    }

}
