package net.masil.testing.compile;

import org.junit.jupiter.api.Test;

import static java.lang.reflect.Modifier.PUBLIC;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SourceFileTest {


    @Test
    void getProperties() {
        SourceFile helloWorld = SourceFile.withName("org.masil.testing.compile.HelloWorld")
                .addModifier(PUBLIC)
                .withEmptyBody();

        assertEquals("org.masil.testing.compile.HelloWorld", helloWorld.getClassName().toString());
        assertEquals("org.masil.testing.compile", helloWorld.getPackageName());
        assertEquals("HelloWorld", helloWorld.getSimpleClassName());
        assertEquals("HelloWorld.java", helloWorld.getFileName());
    }
}