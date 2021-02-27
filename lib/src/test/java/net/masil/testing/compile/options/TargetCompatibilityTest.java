package net.masil.testing.compile.options;

import net.masil.testing.compile.IllegalOptionException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static net.masil.testing.compile.options.TargetCompatibility.OPTION_NAME;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TargetCompatibilityTest {


    @Test
    void illegalOptionException() {
        assertThrows(IllegalOptionException.class, () -> TargetCompatibility.of(null));
    }

    @Test
    void setupOption() {
        assertLinesMatch(toList(OPTION_NAME, "1.8"), toList(TargetCompatibility.of("1.8")));
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


}