package net.masil.testing.compile;

public class Deprecation extends Option{
    private Deprecation() {
        super("-deprecation", null);
    }

    public static Option of() {
        return new Deprecation();
    }
}
