package net.masil.testing.compile;

import net.masil.testing.compile.options.*;

import java.util.ArrayList;
import java.util.List;

public final class OptionListBuilder {

    private List<Option<?>> options;

    private OptionListBuilder() {
        options = new ArrayList<>();
    }

    public static OptionListBuilder builder() {
        return new OptionListBuilder();
    }

    public OptionList build() {
        return new OptionList(options);
    }

    public OptionListBuilder buildDir(String dir) {
        options.add(BuildDir.of(dir));
        return this;
    }

    public OptionListBuilder source(String release) {
        options.add(SourceCompatibility.of(release));
        return this;
    }

    public OptionListBuilder target(String release) {
        options.add(TargetCompatibility.of(release));
        return this;
    }


    public OptionListBuilder deprecation() {
        options.add(Deprecation.of());
        return this;
    }
}
