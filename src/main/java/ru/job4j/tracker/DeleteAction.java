package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "=== Delete item ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        boolean result = false;
        String id = input.askStr("Please, enter item Id what you want delete: ");
        if (tracker.delete(id)) {
            System.out.println("Item " + id + " was deleted");
            result = true;
        } else {
            System.out.println("That Id not found");
        }
        System.out.println("===================");
        return result;
    }
}
