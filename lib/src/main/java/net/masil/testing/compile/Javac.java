package net.masil.testing.compile;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Javac {

    public static final String BUILD_DIRECTORY = "-d";

    private final Map<String, SourceFile> sourceFiles;
    private final List<Option> options;

    public static Javac init() {
        return new Javac();
    }

    private Javac() {
        this.sourceFiles = new HashMap<>();
        this.options = new ArrayList<>();
    }

    public Javac with(SourceFile sourceFile) {
        sourceFiles.put(sourceFile.getSimpleClassName(), sourceFile);
        return this;
    }


    public Compilation compile() {
        try {
            Sources sources = new Sources();
            sourceFiles.forEach((key, value) -> sources.withSourceFile(value));
            sources.compile(options);

        } catch (RuntimeException e) {
            System.out.println(e);
        }

        return new Compilation(options);
    }

    public Javac options(String name, String value) {
        Option option = new Option(name, value);
         this.options.add(option);
        return this;
    }
}
