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
    private Options ops;

    public static Javac init() {
        return new Javac();
    }

    private Javac() {
        this.sourceFiles = new HashMap<>();
        this.ops = Options.init();
    }

    public Javac with(SourceFile ... sourceFiles) {
        Arrays.stream(sourceFiles).forEach(s->
                this.sourceFiles.put(s.getSimpleClassName(), s)
        );

        return this;
    }

    public Javac options(String name, String value) {
        this.ops = this.ops.set(name, value);
        return this;
    }


    public Compilation compile() {

        compile(this.ops, new ArrayList<>(sourceFiles.values()));

        return new Compilation(this.ops);
    }

    void compile(Options options, List<SourceFile> files) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();


        File file = new File(options.buildDir());
        if(!file.exists()) {
            file.mkdirs();
        }

        JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, options.buildOptionStr(), null, files);

        if (COMPILE_FAILED == task.call()) {
            diagnostics.getDiagnostics().forEach(System.out::println);
        }
    }
}
