package net.masil.testing.compile.options;

import net.masil.testing.compile.IllegalOptionException;

import static java.util.Arrays.asList;

public class TargetCompatibility extends AbstractOption<String> {

    public static final String OPTION_NAME = "-target";

    private TargetCompatibility(String release) {
        super(release);
    }

    public static TargetCompatibility of(String release) {
        if(release == null) {
            throw new IllegalOptionException("The Release must not be null.");
        }
        return new TargetCompatibility(release);
    }

    @Override
    public String getName() {
        return OPTION_NAME;
    }

    @Override
    public Iterable<String> toIterable() {
        return asList(OPTION_NAME, getValue());
    }
}
