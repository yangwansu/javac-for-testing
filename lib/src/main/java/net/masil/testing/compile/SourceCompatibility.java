package net.masil.testing.compile;

public class SourceCompatibility extends Option{

    private SourceCompatibility(String release) {
        super("-source", release);
    }

    public static Option of(String release) {
        return new SourceCompatibility(release);
    }
}
