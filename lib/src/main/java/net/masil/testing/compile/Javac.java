package net.masil.testing.compile;

import java.util.HashMap;
import java.util.Map;

public class Javac {

    public static final String BUILD_DIRECTORY = "-d";

    private Map<String,String> sourceFiles;
    private String oName;
    private String oValue;

    public static Javac init() {
        return new Javac();
    }

    private Javac() {
        sourceFiles = new HashMap<>();
    }

    public Javac with(SourceFile sourceFile) {
        sourceFiles.put(sourceFile.getFileName(), sourceFile.toString());
        return this;
    }

    public Javac withSourceFile(String fileName, String codes) {
        sourceFiles.put(fileName, codes);
        return this;
    }

    public Compilation compile() {
        try {
            //TODO  사라져라 뿅 !
            Sources.withClassName("HelloWorld").withLines(sourceFiles.get("HelloWorld")).compile(oName, oValue);
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        return new Compilation(oValue);
    }

    public Javac options(String name, String value) {
        this.oName = name;
        this.oValue = value;
        return this;
    }
}
