package net.masil.testing.compile;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

public class SourceFile extends SimpleJavaFileObject {

    private final ClassName name;
    private String body;

    public static SourceFile withName(String name) {
        return new SourceFile(name);
    }

    private SourceFile(String name) {
        super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.name = ClassName.of(name);
    }

    public SourceFile withBody(String body) {
        this.body = body;
        return this;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return toString();
    }

    @Override
    public String toString() {

        final String packageName = this.name.getPackageName();

        final StringBuilder sb = new StringBuilder();
        if (!"".equals(packageName)) {
            sb.append("package ").append(packageName).append(";").append("\n");
        }
        sb.append(body);

        return sb.toString();

    }

    public ClassName getClassName() {
        return this.name;
    }

    public String getSimpleClassName() {
        return getClassName().getSimpleClassName();
    }
}
