package net.masil.testing.compile.options;

import java.util.Collections;

public class Deprecation extends AbstractOption<Boolean> {
    public static final String OPTION_NAME = "-deprecation";

    private Deprecation() {
        super(true);
    }

    public static Deprecation enable() {
        return of();
    }

    public static Deprecation of() {
        return new Deprecation();
    }

    @Override
    public String getName() {
        return OPTION_NAME;
    }


    @Override
    public Iterable<String> toIterable() {
        return Collections.singletonList(OPTION_NAME);
    }
}
