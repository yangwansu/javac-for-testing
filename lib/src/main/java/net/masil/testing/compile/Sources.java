package net.masil.testing.compile;

import javax.annotation.processing.Processor;
import javax.tools.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

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

        List<JavaFileObject> compilationUnits = files.stream().map(this::javaFileObject).collect(toList());


        JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, options, null, compilationUnits);

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

    private JavaFileObject javaFileObject(SourceFile sourceFile) {
        StringWriter writer = new StringWriter();
        PrintWriter out = new PrintWriter(writer);
        out.println(sourceFile.toString());
        out.close();
        return new JavaSourceFromString(sourceFile.getClassName().toString(), writer.toString());
    }

    static class JavaSourceFromString extends SimpleJavaFileObject {
        final String code;

        JavaSourceFromString(String name, String code) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }
}
