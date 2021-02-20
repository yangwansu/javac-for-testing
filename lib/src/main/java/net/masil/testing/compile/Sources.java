package net.masil.testing.compile;

import javax.annotation.processing.Processor;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

class Sources {

    public static final boolean COMPILE_FAILED = false;
    private final List<Class<? extends Processor>> processors;
    private List<SourceFile> files;

    public Sources() {
        this.processors = new ArrayList<>();
        this.files = new ArrayList<>();
    }


    public Sources withSourceFile(SourceFile sourceFile) {
        this.files.add(sourceFile);
        return this;
    }

    public Sources withProcessor(Class<? extends Processor> processorClass) {
        processors.add(processorClass);
        return this;
    }

    public void compile(String optionName, String optionValue) {
        List<String> options = new ArrayList<>();
        processorOption(options);

        options.add(optionName);

        File file1 = new File(optionValue);
        file1.mkdirs();
        options.add(file1.getAbsolutePath());

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();



        JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, options, null, files);

        if (COMPILE_FAILED == task.call()) {

            diagnostics.getDiagnostics().forEach(System.err::println);

            throw new RuntimeException();
        }


    }

    private void processorOption(List<String> options) {
        if (processors.isEmpty()) return;
        options.add("-processor");
        String list = processors.stream().reduce("", (s, aClass) -> {
            StringBuilder sb = new StringBuilder(s);
            sb.append(aClass.getName());
            return sb.toString();
        }, (s, s2) -> s2);
        options.add(list);
    }


}
