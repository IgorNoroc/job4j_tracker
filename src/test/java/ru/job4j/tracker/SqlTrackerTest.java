package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SqlTrackerTest {
    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name"));
            assertThat(tracker.findByName("name").size(), is(1));
        }
    }

    @Test
    public void showAllItems() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.clearTableItems();
            Item first = tracker.add(new Item("name"));
            Item second = tracker.add(new Item("user"));
            Item expectedFirst = new Item("name");
            expectedFirst.setId(first.getId());
            Item expectedSecond = new Item("user");
            expectedSecond.setId(second.getId());
            List<Item> expected = List.of(expectedFirst, expectedSecond);
            List<Item> result = tracker.findAll();
            assertThat(expected, is(result));
        }
    }

    @Test
    public void deleteItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.clearTableItems();
            Item item = new Item("name");
            tracker.add(item);
            List<Item> list = tracker.findAll();
            tracker.delete(list.get(0).getId());
            assertThat(tracker.findAll().size(), is(0));
        }
    }

    @Test
    public void replaceItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("name");
            Item rsl = tracker.add(item);
            tracker.replace(rsl.getId(), new Item("user"));
            assertThat(tracker.findById(rsl.getId()).getName(), is("user"));
        }
    }

    @Test
    public void findByIdItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("name");
            Item expected = tracker.add(item);
            assertThat(tracker.findById(expected.getId()).getName(), is("name"));
        }
    }

    @Test
    public void findByNameItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item1 = new Item("name");
            Item item2 = new Item("user");
            Item item3 = new Item("name");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            assertThat(tracker.findByName("name").size(), is(2));
        }
    }
}