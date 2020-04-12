package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tracker {
    private final List<Item> items = new ArrayList<>();

    /**
     * Метод добавления заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(generateId());
        items.add(item);
        return item;
    }

    /**
     * Получаем список заявок.
     *
     * @return список активных заявок.
     */
    public List<Item> findAll() {
        return items;
    }

    /**
     * Ищем заявки в хранилище по имени.
     *
     * @param key имя
     * @return массив из совпавших имён.
     */
    public List<Item> findByName(String key) {
        List<Item> rst = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(key)) {
                rst.add(item);
            }
        }
        return rst;
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
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                rsl = i;
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
        return index != -1 ? items.get(index) : null;
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
            item.setId(items.get(index).getId());
            items.set(index, item);
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
            items.remove(index);
        }
        return index != -1;
    }
}