package net.masil.testing.compile;

import org.junit.jupiter.api.Test;

import static net.masil.testing.compile.Options.DEFAULT_BUILD_DIR;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionsTest {


    @Test
    void buildDir() {
        Options op1 = new Options();

        String buildDir = "foo/build/test/classes";
        Options op2 = op1.set(Options.BUILD_DIRECTORY, buildDir);

        assertEquals(DEFAULT_BUILD_DIR, op1.getBuildDir());
        assertEquals(buildDir, op2.getBuildDir());
    }
}