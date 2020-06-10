package ru.job4j.tracker;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 2.1. Реализация класса StartUI[#257548]
 */
public class StartUI {
    private static Connection cn;

    /**
     * Иницилизация меню.
     *
     * @param input   Входные данные
     * @param tracker хранилище
     * @param actions список действий
     */
    public void init(Input input, Store tracker, List<UserAction> actions) {
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

    /**
     * Подключаемся к базе данных.
     */
    public static Connection initConnect() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return cn;
    }

    public static void main(String[] args) {
        Input input = new ValidateInput(new ConsoleInput());
        try (Store tracker = new SqlTracker(initConnect())) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
