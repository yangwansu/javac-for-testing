package net.masil.testing.compile;

import java.util.ArrayList;
import java.util.List;

public final class OptionBuilder {

    private List<Option> options;

    private OptionBuilder() {
        options = new ArrayList<>();
    }

    public static OptionBuilder builder() {
        return new OptionBuilder();
    }

    public Options build() {
        return new Options(options);
    }

    public OptionBuilder buildDir(String dir) {
        options.add(BuildDir.of(dir));
        return this;
    }

    public OptionBuilder source(String release) {
        options.add(SourceCompatibility.of(release));
        return this;
    }

    public OptionBuilder target(String release) {
        options.add(TargetCompatibility.of(release));
        return this;
    }


    public OptionBuilder deprecation() {
        options.add(Deprecation.of());
        return this;
    }
}
