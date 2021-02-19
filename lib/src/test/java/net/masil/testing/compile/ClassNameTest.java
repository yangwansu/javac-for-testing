package net.masil.testing.compile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassNameTest {



    @Test
    void getProperties() {
        ClassName actual = ClassName.of("org.masil.testing.compile.HelloWorld");


        assertEquals("org.masil.testing.compile.HelloWorld", actual.toString());
        assertEquals("org.masil.testing.compile", actual.getPackageName());
        assertEquals("HelloWorld", actual.getSimpleClassName());
        assertEquals("HelloWorld.java", actual.getFileName());
    }
}
