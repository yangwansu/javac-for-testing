package net.masil.testing.compile.options;

import net.masil.testing.compile.IllegalOptionException;
import net.masil.testing.compile.Option;

import java.util.Collections;

import static java.util.Arrays.asList;

public class BuildDir extends AbstractOption<String> {

    public static final String OPTION_NAME = "-d";
    public static final String DEFAULT_BUILD_DIR = "build/test/classes";
    public static final BuildDir DEFAULT_OPTION = new BuildDir(DEFAULT_BUILD_DIR);


    public static BuildDir of(String dir) {
        if(dir == null) {
            throw new IllegalOptionException("The Build dir must not be null.");
        }
        return new BuildDir(dir);
    }

    public static BuildDir of() {
        return new BuildDir("");
    }

    public static BuildDir defaultOption() {
        return DEFAULT_OPTION;
    }


    private BuildDir(String value) {
        super(value);
    }

    @Override
    public String getName() {
        return OPTION_NAME;
    }

    public Iterable<String> toIterable() {
        if("".equals(getValue().trim())) {
            return Collections.emptyList();
        }
        return asList(OPTION_NAME, getValue());
    }
}
