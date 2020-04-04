package ru.job4j.tracker;

public class ExitAction implements UserAction {
    /**
     * Назвние команды.
     *
     * @return описание действия.
     */
    @Override
    public String name() {
        return "=== Exit ===";
    }

    /**
     * Выход из программы.
     *
     * @param input   входящий поток.
     * @param tracker хранилище.
     * @return выход.
     */
    @Override
    public boolean execute(Input input, Tracker tracker) {
        return false;
    }
}
