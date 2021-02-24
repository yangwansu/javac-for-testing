package net.masil.testing.compile;

public class BuildDir extends Option {

    private BuildDir(String value) {
        super(Options.BUILD_DIRECTORY, value);
    }

    public static Option of(String dir) {
        return new BuildDir(dir);
    }
}
