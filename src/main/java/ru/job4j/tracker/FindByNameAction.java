package ru.job4j.tracker;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "=== Find by name items ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        Item[] items = tracker.findByName(input.askStr("Please, enter name to search: "));
        System.out.println("=== All items of you search ===");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("===================");
        return items.length > 0;
    }
}
