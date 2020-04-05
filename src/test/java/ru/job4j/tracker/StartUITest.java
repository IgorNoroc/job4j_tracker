package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class StartUITest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenPrtMenu() {
        StubInput input = new StubInput(
                new String[] {"0"}
        );
        StubAction action = new StubAction();
        new StartUI().init(input, new Tracker(), new UserAction[] { action });
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("Menu.")
                .add("0. Stub action")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
    }

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
        assertThat(items, is(expected));
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
                        new FindAllAction(),
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

    @Test
    public void whenDeleted() {
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
                        new DeleteAction(),
                        new ExitAction()});
        Item expected = tracker.findById(id);
        assertNull(expected);
    }
}
