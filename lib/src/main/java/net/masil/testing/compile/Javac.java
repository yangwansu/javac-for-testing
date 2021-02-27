package net.masil.testing.compile;


import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.io.File;
import java.util.*;

public class Javac {

    public static final boolean COMPILE_FAILED = false;


    private final Map<String, SourceFile> sourceFiles;
    private OptionList optionList;

    public static Javac init() {
        return new Javac();
    }

    private Javac() {
        this.sourceFiles = new HashMap<>();
        this.optionList = OptionListBuilder.builder().build();
    }

    public Javac with(SourceFile ... sourceFiles) {
        Arrays.stream(sourceFiles).forEach(s->
                this.sourceFiles.put(s.getSimpleClassName(), s)
        );

        return this;
    }

    public Compilation compile() {

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

        File file = new File(optionList.buildDir());
        if(!file.exists()) {
            file.mkdirs();
        }

        List<String> options1 = optionList.toList();

        JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, options1, null, new ArrayList<SourceFile>(sourceFiles.values()));

        if (COMPILE_FAILED == task.call()) {
            diagnostics.getDiagnostics().forEach(System.out::println);
        }

        return new Compilation(optionList);
    }

    public Javac options(OptionListBuilder optionBuilder) {
        this.optionList = optionBuilder.build();
        return this;
    }
}
