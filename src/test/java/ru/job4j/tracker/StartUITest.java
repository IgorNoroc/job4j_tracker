package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class StartUITest {
//    @Test
//    public void whenAddItem() {
//        String[] answers = {"Fix PC"};
//        Input input = new StubInput(answers);
//        Tracker tracker = new Tracker();
//        UserAction[] actions = {new CreateAction()};
//        StartUI startUI = new StartUI();
//        startUI.init(input, tracker, actions);
//        Item created = tracker.findAll()[0];
//        Item expected = new Item("Fix PC");
//        assertThat(created.getName(), is(expected.getName()));
//    }

//    @Test
//    public void whenCreateItem() {
//        String[] answers = {"Igor", "Tanea", "Olea"};
//        Input input = new StubInput(answers);
//        Tracker tracker = new Tracker();
//        StartUI.createItem(input, tracker);
//        StartUI.createItem(input, tracker);
//        StartUI.createItem(input, tracker);
//        Item[] items = tracker.findAll();
//        Item[] expected = {new Item("Igor"), new Item("Tanea"), new Item("Olea")};
//        assertThat(items[0].getName(), is(expected[0].getName()));
//        assertThat(items[1].getName(), is(expected[1].getName()));
//        assertThat(items[2].getName(), is(expected[2].getName()));
//    }
//
//    @Test
//    public void whenReplaceItem() {
//        Tracker tracker = new Tracker();
//        Item item = new Item("Igor");
//        tracker.add(item);
//        String[] answer = {
//                item.getId(), "Tanea"
//        };
//        StartUI.replaceItem(new StubInput(answer), tracker);
//        Item replaced = tracker.findById(item.getId());
//        assertThat(replaced.getName(), is("Tanea"));
//    }
//
//    @Test
//    public void whenDeleteItem() {
//        Tracker tracker = new Tracker();
//        Item item = new Item("Igor");
//        tracker.add(item);
//        String[] answer = {
//                item.getId()
//        };
//        StartUI.deleteItem(new StubInput(answer), tracker);
//        Item deleted = tracker.findById(item.getId());
//        assertNull(deleted);
//    }
}
