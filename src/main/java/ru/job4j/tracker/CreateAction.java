package ru.job4j.tracker;

/**
 * 8. Реализация меню за счет шаблона стратегия.[#257545]
 */
public class CreateAction implements UserAction {
    /**
     * Назвние команды.
     *
     * @return описание действия.
     */
    @Override
    public String name() {
        return "=== Create a new Item ====";
    }

    /**
     * Создаём новую заявку.
     *
     * @param input входящий поток.
     * @param tracker хранилище.
     * @return истини или нет.
     */
    @Override
    public boolean execute(Input input, Store tracker) {
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
        return true;
    }
}
