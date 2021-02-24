package net.masil.testing.compile;

public class TargetCompatibility extends Option{

    private TargetCompatibility(String release) {
        super("-target", release);
    }

    public static Option of(String release) {
        return new TargetCompatibility(release);
    }
}
