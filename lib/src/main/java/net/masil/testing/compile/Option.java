package net.masil.testing.compile;

public class Option {
    private final String name;
    private final String value;

    Option(String name, String value) {
        this.name = name;
        this.value = value;
    }


    String getName() {
        return name;
    }

    String getValue() {
        return value;
    }
}
