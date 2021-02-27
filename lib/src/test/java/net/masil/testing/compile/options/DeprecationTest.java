package net.masil.testing.compile.options;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

class DeprecationTest {


    @Test
    void enable() {
        assertLinesMatch(toList(Deprecation.OPTION_NAME), toList(Deprecation.enable()));
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