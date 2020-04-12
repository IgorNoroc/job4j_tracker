package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemTest {
    @Test
    public void whenOrderByName() {
        List<Item> items = new ArrayList<>(
                Arrays.asList(
                        new Item("IGor"),
                        new Item("Vasea"),
                        new Item("Olea"),
                        new Item("Tanea")
                )
        );
        List<Item> rsl = new ArrayList<>(
                Arrays.asList(
                        new Item("IGor"),
                        new Item("Olea"),
                        new Item("Tanea"),
                        new Item("Vasea")
                )
        );
        Collections.sort(items);
        assertThat(rsl, is(items));
    }

    @Test
    public void whenReverseOrder() {
        List<Item> items = new ArrayList<>(
                Arrays.asList(
                        new Item("user1"),
                        new Item("user3"),
                        new Item("user4"),
                        new Item("user2")
                )
        );
        List<Item> rsl = new ArrayList<>(
                Arrays.asList(
                        new Item("user4"),
                        new Item("user3"),
                        new Item("user2"),
                        new Item("user1")
                )
        );
        items.sort(Collections.reverseOrder());
        assertThat(rsl, is(items));
    }
}

