package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    @Test
    public void whenCreateItem() {
        StubInput input = new StubInput(
                new String[]{"0", "new item", "1"}
        );
        Tracker tracker = new Tracker();
        Item item = new Item("new item");
        new StartUI().init(input, tracker, new UserAction[]{new CreateAction(), new ExitAction()});
        Item expected = tracker.findAll()[0];
        assertThat(item.getName(), is(expected.getName()));
    }

    @Test
    public void whenFindByName() {
        StubInput input = new StubInput(
                new String[]{"0", "new item",
                        "0", "new item2",
                        "0", "new item3",
                        "0", "new item",
                        "1", "new item",
                        "2"
                }
        );
        Tracker tracker = new Tracker();
        Item[] items = new Item[]{
                new Item("new item"),
                new Item("new item")
        };
        new StartUI().init(input, tracker,
                new UserAction[]{
                        new CreateAction(),
                        new FindByNameAction(),
                        new ExitAction()});
        Item[] expected = tracker.findByName("new item");
        assertThat(items[0].getName(), is(expected[0].getName()));
        assertThat(items[1].getName(), is(expected[1].getName()));
    }

    @Test
    public void whenShowAllItems() {
        StubInput input = new StubInput(
                new String[]{"0", "new item",
                        "0", "new item2",
                        "0", "new item3",
                        "0", "new item",
                        "1",
                        "2"
                }
        );
        Tracker tracker = new Tracker();
        Item[] items = new Item[]{
                new Item("new item"),
                new Item("new item2"),
                new Item("new item3"),
                new Item("new item")
        };
        new StartUI().init(input, tracker,
                new UserAction[]{
                        new CreateAction(),
                        new ShowAllItemsAction(),
                        new ExitAction()});
        Item[] expected = tracker.findAll();
        assertThat(items.length, is(expected.length));
    }

    @Test
    public void whenFindById() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("need item"));
        String id = tracker.findAll()[0].getId();
        StubInput input = new StubInput(
                new String[]{"0", "new item",
                        "0", "new item2",
                        "0", "new item3",
                        "0", "new item",
                        "1", id,
                        "2"
                }
        );
        new StartUI().init(input, tracker,
                new UserAction[]{
                        new CreateAction(),
                        new FindByIdAction(),
                        new ExitAction()});
        Item need = tracker.findAll()[0];
        Item expected = new Item("need item");
        assertThat(need.getName(), is(expected.getName()));
    }
}
