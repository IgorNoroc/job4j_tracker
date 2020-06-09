package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    @Ignore
    public void whenPrtMenu() {
//        StubInput input = new StubInput(
//                new String[]{"0"}
//        );
//        StubAction action = new StubAction();
//        List<UserAction> actions = new ArrayList<>();
//        actions.add(action);
//        new StartUI().init(input, new MemTracker(), actions);
//        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
//                .add("Menu.")
//                .add("0. Stub action")
//                .toString();
//        assertThat(new String(out.toByteArray()), is(expect));
//    }
//
//    @Test
//    @Ignore
//    public void whenCreateItem() {
//        StubInput input = new StubInput(
//                new String[]{"0", "new item", "1"}
//        );
//        MemTracker tracker = new MemTracker();
//        Item item = new Item("new item");
//        new StartUI().init(input, tracker,
//                new ArrayList<>(
//                        Arrays.asList(
//                                new CreateAction(),
//                                new ExitAction())));
//        Item expected = tracker.findAll().get(0);
//        assertThat(item.getName(), is(expected.getName()));
//    }
//
//    @Test
//    @Ignore
//    public void whenFindByName() {
//        StubInput input = new StubInput(
//                new String[]{"0", "new item",
//                        "0", "new item2",
//                        "0", "new item3",
//                        "0", "new item",
//                        "1", "new item",
//                        "2"
//                }
//        );
//        MemTracker tracker = new MemTracker();
//        List<Item> items = new ArrayList<>(
//                Arrays.asList(
//                        new Item("new item"),
//                        new Item("new item")
//                )
//        );
//        new StartUI().init(input, tracker,
//                new ArrayList<>(
//                        Arrays.asList(
//                                new CreateAction(),
//                                new FindByNameAction(),
//                                new ExitAction())
//                )
//        );
//        List<Item> expected = tracker.findByName("new item");
//        assertThat(items, is(expected));
//    }
//
//    @Test
//    @Ignore
//    public void whenShowAllItems() {
//        StubInput input = new StubInput(
//                new String[]{"0", "new item",
//                        "0", "new item2",
//                        "0", "new item3",
//                        "0", "new item",
//                        "1",
//                        "2"
//                }
//        );
//        MemTracker tracker = new MemTracker();
//        List<Item> items = new ArrayList<>(
//                Arrays.asList(
//                        new Item("new item"),
//                        new Item("new item2"),
//                        new Item("new item3"),
//                        new Item("new item")
//                )
//        );
//        new StartUI().init(input, tracker,
//                new ArrayList<>(
//                        Arrays.asList(
//                                new CreateAction(),
//                                new FindAllAction(),
//                                new ExitAction())
//                )
//        );
//        List<Item> expected = tracker.findAll();
//        assertThat(items, is(expected));
//    }
//
//    @Test
//    @Ignore
//    public void whenFindById() {
//        MemTracker tracker = new MemTracker();
//        tracker.add(new Item("need item"));
//        String id = tracker.findAll().get(0).getId();
//        StubInput input = new StubInput(
//                new String[]{"0", "new item",
//                        "0", "new item2",
//                        "0", "new item3",
//                        "0", "new item",
//                        "1", id,
//                        "2"
//                }
//        );
//        new StartUI().init(input, tracker,
//                new ArrayList<>(
//                        Arrays.asList(
//                                new CreateAction(),
//                                new FindByIdAction(),
//                                new ExitAction())
//                )
//        );
//        Item need = tracker.findAll().get(0);
//        Item expected = new Item("need item");
//        assertThat(need.getName(), is(expected.getName()));
//    }
//
//    @Test
//    @Ignore
//    public void whenDeleted() {
//        MemTracker tracker = new MemTracker();
//        tracker.add(new Item("need item"));
//        String id = tracker.findAll().get(0).getId();
//        StubInput input = new StubInput(
//                new String[]{"0", "new item",
//                        "0", "new item2",
//                        "0", "new item3",
//                        "0", "new item",
//                        "1", id,
//                        "2"
//                }
//        );
//        new StartUI().init(input, tracker,
//                new ArrayList<>(
//                        Arrays.asList(
//                                new CreateAction(),
//                                new DeleteAction(),
//                                new ExitAction())
//                )
//        );
//        Item expected = tracker.findById(id);
//        assertNull(expected);
    }
}
