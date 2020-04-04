package ru.job4j.tracker;

public class FindByIdAction implements UserAction {
    @Override
    public String name() {
        return "=== Find by id ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        Item item = tracker.findById(input.askStr("Please, enter item Id: "));
        System.out.println("Found item : " + item);
        System.out.println("===================");
        return item != null;
    }
}
