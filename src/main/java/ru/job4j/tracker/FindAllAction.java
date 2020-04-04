package ru.job4j.tracker;

public class FindAllAction implements UserAction {
    /**
     * Назвние команды.
     *
     * @return описание действия.
     */
    @Override
    public String name() {
        return "=== Show all items ===";
    }

    /**
     * Выводим список заявок в консоль.
     *
     * @param input   входящие данные.
     * @param tracker хранилище.
     * @return получилась операция или нет.
     */
    @Override
    public boolean execute(Input input, Tracker tracker) {
        for (Item item : tracker.findAll()) {
            System.out.println(String.format("%s %s", item.getId(), item.getName()));
        }
        return true;
    }
}
