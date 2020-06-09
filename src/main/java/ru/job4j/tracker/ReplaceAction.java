package ru.job4j.tracker;


public class ReplaceAction implements UserAction {
    /**
     * Назвние команды.
     *
     * @return описание действия.
     */
    @Override
    public String name() {
        return "=== Replace item ===";
    }

    /**
     * Редактировние заявки в хранилище.
     *
     * @param input   входящие данные.
     * @param tracker хранилище.
     * @return получилось или нет отредактировать.
     */
    @Override
    public boolean execute(Input input, Store tracker) {
        boolean result = false;
        String id = input.askStr("Please, enter Id of need item: ");
        Item item = new Item(input.askStr("Please, enter new item: "));
        if (tracker.replace(id, item)) {
            System.out.println("Edit was successful!");
            result = true;
        } else {
            System.out.println("Please, check you enter data. Enter was unsuccessful");
        }
        return result;
    }
}
