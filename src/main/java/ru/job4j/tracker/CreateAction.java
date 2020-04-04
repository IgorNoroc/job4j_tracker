package ru.job4j.tracker;

/**
 * 8. Реализация меню за счет шаблона стратегия.[#257545]
 */
public class CreateAction implements UserAction {

    @Override
    public String name() {
        return "=== Create a new Item ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
        return true;
    }
}
