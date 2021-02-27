package net.masil.testing.compile.options;

import net.masil.testing.compile.Option;

public abstract class AbstractOption<T> implements Option<T> {
    private final T value;

    public AbstractOption(T value) {
        this.value = value;
    }


    public T getValue() {
        return value;
    }


}
