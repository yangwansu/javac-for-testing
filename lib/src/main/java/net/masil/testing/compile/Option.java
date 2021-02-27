package net.masil.testing.compile;

public interface Option<T> {
    String getName();
    T getValue();
    Iterable<String> toIterable();

}
