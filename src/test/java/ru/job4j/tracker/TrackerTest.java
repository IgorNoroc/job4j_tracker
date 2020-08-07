package ru.job4j.tracker;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Ignore
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Ignore
    @Test
    public void whenFindAllTheSameNames() {
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("Igor");
        Item item2 = new Item("Vasea");
        Item item3 = new Item("Sasha");
        Item item4 = new Item("Igor");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        List<Item> result = tracker.findByName(item1.getName());
        List<Item> expected = new ArrayList<>(
                Arrays.asList(
                        item1,
                        item4
                )
        );
        assertThat(result, is(expected));
    }

    @Ignore
    @Test
    public void whenFindAll() {
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("Igor");
        Item item4 = new Item("Igor");
        tracker.add(item1);
        tracker.add(item4);
        List<Item> result = tracker.findAll();
        List<Item> expected = new ArrayList<>(
                Arrays.asList(
                        item1,
                        item4
                )
        );
        assertThat(result, is(expected));
    }

    @Ignore
    @Test
    public void whenReplace() {
        MemTracker tracker = new MemTracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }

    @Ignore
    @Test
    public void whenDelete() {
        MemTracker tracker = new MemTracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        int id = bug.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id), is(nullValue()));
    }
}