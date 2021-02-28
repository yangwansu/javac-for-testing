package net.masil.testing.compile;

import java.util.HashMap;
import java.util.Map;

public class Javac {


    private Map<String,String> sourceFiles;

    public static Javac init() {
        return new Javac();
    }

    private Javac() {
        sourceFiles = new HashMap<>();
    }

    public Javac withSourceFile(String fileName, String codes) {
        sourceFiles.put(fileName, codes);
        return this;
    }

    public Compilation compile() {
        try {
            Sources.withClassName("HelloWorld").withLines(sourceFiles.get("HelloWorld")).compile();
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        return new Compilation();
    }
}
