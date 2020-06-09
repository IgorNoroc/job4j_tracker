package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    /**
     * Назвние команды.
     *
     * @return описание действия.
     */
    @Override
    public String name() {
        return "=== Delete item ===";
    }

    /**
     * Удаление заявки из хранилища.
     *
     * @param input   входящие данные.
     * @param tracker хранилище.
     * @return получилось или нет.
     */
    @Override
    public boolean execute(Input input, Store tracker) {
        boolean result = false;
        String id = input.askStr("Please, enter item Id what you want delete: ");
        if (tracker.delete(id)) {
            System.out.println("Item " + id + " was deleted");
            result = true;
        } else {
            System.out.println("That Id not found");
        }
        return result;
    }
}
