package net.masil.testing.compile;

public class ClassName {
    private final String className;

    public ClassName(String className) {
        this.className = className;
    }

    public String getSimpleClassName() {
        final int i1 = className.lastIndexOf(".");
        return i1 == -1 ? className : className.substring(i1+1);
    }

    public String getPackageName() {
        final int i0 = className.lastIndexOf(".");
        return i0 == -1 ? "" : className.substring(0, i0);
    }

    @Override
    public String toString() {
        return className;
    }

    public static ClassName of(String className) {
        return new ClassName(className);
    }

    public String getFileName() {
        return getSimpleClassName() + ".java";
    }
}
