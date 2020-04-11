package ru.job4j.tracker;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2.1. Реализация класса StartUI[#257548]
 */
public class StartUI {
    /**
     * Иницилизация меню.
     *
     * @param input   Входные данные
     * @param tracker хранилище
     * @param actions список действий
     */
    public void init(Input input, Tracker tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ", actions.size());
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);
        }
    }

    /**
     * Выводим список возоможных действий.
     *
     * @param actions список действий.
     */
    private void showMenu(List<UserAction> actions) {
        System.out.println("Menu.");
        for (int index = 0; index < actions.size(); index++) {
            System.out.println(index + ". " + actions.get(index).name());
        }
    }

    public static void main(String[] args) {
        Input input = new ValidateInput(new ConsoleInput());
        Tracker tracker = new Tracker();
        List<UserAction> actions = new ArrayList<>(
                Arrays.asList(
                        new CreateAction(),
                        new FindAllAction(),
                        new ReplaceAction(),
                        new DeleteAction(),
                        new FindByIdAction(),
                        new FindByNameAction(),
                        new ExitAction()
                )
        );
        new StartUI().init(input, tracker, actions);
    }
}
