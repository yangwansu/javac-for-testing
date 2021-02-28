package net.masil.testing.compile;

public class ClassName {
    private final String className;

    public ClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return className;
    }

    public static ClassName of(String className) {
        return new ClassName(className);
    }
}
