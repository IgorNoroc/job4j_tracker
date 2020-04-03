package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

public class Tracker {
    private Item[] items = new Item[100];
    private int position = 0;

    /**
     * Метод добавления заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(generateId());
        items[position++] = item;
        return item;
    }

    /**
     * Получаем список заявок.
     *
     * @return список активных заявок.
     */
    public Item[] findAll() {
        return Arrays.copyOf(items, position);
    }

    /**
     * Ищем заявки в хранилище по имени.
     *
     * @param key имя
     * @return массив из совпавших имён.
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[position];
        int size = 0;
        for (int i = 0; i < position; i++) {
            if (items[i].getName().equals(key)) {
                result[size++] = items[i];
            }
        }
        return Arrays.copyOf(result, size);
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    /**
     * Поиск индекса по id
     *
     * @param id Id
     * @return нужный индекс.
     */
    private int indexOf(String id) {
        int rsl = -1;
        for (int index = 0; index < position; index++) {
            if (items[index].getId().equals(id)) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    /**
     * Поиск в хранилище по идентификатору.
     *
     * @param id идентификатор.
     * @return найденное совпадение.
     */
    public Item findById(String id) {
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
    }

    /**
     * Замена заявки.
     *
     * @param id   старая заявка.
     * @param item новая заявка.
     */
    public boolean replace(String id, Item item) {
        int index = indexOf(id);
        if (index != -1) {
            item.setId(items[index].getId());
            items[index] = item;
        }
        return index != -1;
    }

    /**
     * Удаляем заявку.
     *
     * @param id идентификатор заявки.
     */
    public boolean delete(String id) {
        int index = indexOf(id);
        if (index != -1) {
            System.arraycopy(items, index + 1, items, index, (position - index));
            items[position - 1] = null;
            position--;
        }
        return index != -1;
    }
}