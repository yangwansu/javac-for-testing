package net.masil.testing.compile.options;

import net.masil.testing.compile.IllegalOptionException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static net.masil.testing.compile.options.BuildDir.DEFAULT_BUILD_DIR;
import static net.masil.testing.compile.options.BuildDir.OPTION_NAME;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BuildDirTest {

    @Test
    void illegalOptionException() {
        assertThrows(IllegalOptionException.class, () -> BuildDir.of(null));
    }

    @Test
    void setupOption() {
        assertLinesMatch(empty(), toList(BuildDir.of()));
        assertLinesMatch(empty(), toList(BuildDir.of("")));
        assertLinesMatch(empty(), toList(BuildDir.of("  ")));
        assertLinesMatch(toList(OPTION_NAME, DEFAULT_BUILD_DIR), toList(BuildDir.defaultOption()));
        assertLinesMatch(toList(OPTION_NAME, "./"), toList(BuildDir.of("./")));
        assertLinesMatch(toList(OPTION_NAME, "/build/classes"), toList(BuildDir.of("/build/classes")));
    }

    private List<String> toList(AbstractOption option) {
        return toList(option.toIterable());
    }

    private List<String> toList(Iterable<String> iter) {
        return StreamSupport.stream(iter.spliterator(), false).collect(Collectors.toList());
    }

    private List<String> toList(String... str) {
        return Collections.unmodifiableList(Arrays.asList(str));
    }

    private List<String> empty() {
        return Collections.unmodifiableList(new ArrayList<>());
    }
}