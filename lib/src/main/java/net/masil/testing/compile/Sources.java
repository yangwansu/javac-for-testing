package net.masil.testing.compile;

import javax.annotation.processing.Processor;
import javax.tools.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Sources {

    public static final boolean COMPILE_FAILED = false;
    private final List<Class<? extends Processor>> processors;
    private String[] lines;
    private String className;

    public static Sources withClassName(String className) {
        return new Sources(className);
    }

    public Sources(String className) {
        this.className = className;
        this.processors = new ArrayList<>();
    }

    public Sources withLines(String... lines) {
        this.lines = lines;
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
        JavaFileObject file = javaFileObject(lines);
        Iterable<? extends JavaFileObject> compilationUnits = Collections.singletonList(file);

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

    private JavaFileObject javaFileObject(String... lines) {
        StringWriter writer = new StringWriter();
        PrintWriter out = new PrintWriter(writer);
        for (String line : lines) {
            out.println(line);
        }
        out.close();
        return new JavaSourceFromString(this.className, writer.toString());
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
