package ru.job4j.tracker;

/**
 * 8. Реализация меню за счет шаблона стратегия.[#257545]
 */
public interface UserAction {
    String name();

    boolean execute(Input input, Tracker tracker);
}
