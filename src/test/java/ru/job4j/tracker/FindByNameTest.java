package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindByNameTest {
    @Test
    public void whenFindByName() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Tracker tracker = new Tracker();
        Item first = new Item("Igor");
        Item second = new Item("Evlampii");
        Item third = new Item("Igor");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        FindByNameAction action = new FindByNameAction();
        action.execute(new StubInput(new String[]{"Igor"}), tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(first.getName())
                .add(third.getName())
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(stdout);
    }
}
