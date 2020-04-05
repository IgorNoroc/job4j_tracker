package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidateInputTest {
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
    public void whenInvalidInput() {
        String[] data = {"one", "1"};
        ValidateInput input = new ValidateStubInput(data);
        input.askInt("Enter");
        assertThat(
                out.toString(),
                is(String.format("Please enter validate data again.%n"))
        );
    }

    @Test
    public void whenMaxInput() {
        String[] data = {"3", "1"};
        ValidateStubInput input = new ValidateStubInput(data);
        input.askInt("s", 3);
        assertThat(
                out.toString(),
                is(String.format("Please select key from menu.%n"))
        );
    }
}
