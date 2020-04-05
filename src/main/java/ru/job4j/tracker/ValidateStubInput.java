package ru.job4j.tracker;

/**
 * 1.1. Тесты на ValidateInput.[#257561]
 */
public class ValidateStubInput extends ValidateInput {
    private String[] data;
    private int position;

    public ValidateStubInput(String[] data) {
        this.data = data;
    }

    @Override
    public String askStr(String question) {
        return data[position++];
    }
}
