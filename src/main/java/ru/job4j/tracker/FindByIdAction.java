package ru.job4j.tracker;

public class FindByIdAction implements UserAction {
    /**
     * Назвние команды.
     *
     * @return описание действия.
     */
    @Override
    public String name() {
        return "=== Find by id ===";
    }

    /**
     * Находим по идентификатору заявку.
     *
     * @param input   входящие данные.
     * @param tracker хранилище.
     * @return истина или ложь.
     */
    @Override
    public boolean execute(Input input, Store tracker) {
        Item item = tracker.findById(input.askStr("Please, enter item Id: "));
        System.out.println("Found item : " + item);
        return item != null;
    }
}
