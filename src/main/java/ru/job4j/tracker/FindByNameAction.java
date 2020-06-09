package ru.job4j.tracker;

import java.util.List;

public class FindByNameAction implements UserAction {
    /**
     * Назвние команды.
     *
     * @return описание действия.
     */
    @Override
    public String name() {
        return "=== Find by name items ===";
    }

    /**
     * Находим по имени список заявок.
     * @param input входящий поток.
     * @param tracker хранилище.
     * @return истина или ложь.
     */
    @Override
    public boolean execute(Input input, Store tracker) {
        List<Item> items = tracker.findByName(input.askStr("Please, enter name to search: "));
        for (Item item : items) {
            System.out.println(item);
        }
        return items.size() > 0;
    }
}
