package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StubInputTest {
    @Test
    public void whenExit() {
        StubInput input = new StubInput(
                new String[]{"0"}
        );
        StubAction action = new StubAction();
        new StartUI().init(input, new Tracker(), new ArrayList<>(
                        Arrays.asList(action)
                )
        );
        assertThat(action.isCall(), is(true));
    }
}
