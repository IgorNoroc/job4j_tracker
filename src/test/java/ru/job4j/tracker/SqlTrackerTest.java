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
            tracker.add(new Item("name"));
            tracker.add(new Item("user"));
            List<Item> expected = List.of(
                    new Item("name"),
                    new Item("user")
            );
            assertThat(tracker.findAll(), is(expected));
        }
    }

    @Test
    public void deleteItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
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
            tracker.add(item);
            List<Item> list = tracker.findAll();
            tracker.replace(list.get(0).getId(), new Item("user"));
            list = tracker.findAll();
            assertThat(list.get(0).getName(), is("user"));
        }
    }

    @Test
    public void findByIdItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("name");
            tracker.add(item);
            List<Item> list = tracker.findAll();
            String rsl = list.get(0).getId();
            assertThat(tracker.findById(rsl).getName(), is("name"));
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