package net.masil.testing.compile;

import java.util.HashSet;
import java.util.Set;

public class SourceFile {

    private final String name;
    private final Set<Integer> modifier;
    private String body;

    public static SourceFile withName(String name) {
        return new SourceFile(name);
    }

    private SourceFile(String name) {
        this.name = name;
        modifier = new HashSet<>();
    }

    public SourceFile addModifier(int modifier) {
        this.modifier.add(modifier);
        return this;
    }

    public SourceFile withEmptyBody() {
        return withBody("");
    }

    public SourceFile withBody(String body) {
        this.body = body;
        return this;
    }

    @Override
    public String toString() {

        final int i0 = name.lastIndexOf(".");
        final String packageName = i0 == -1 ? "" : name.substring(0, i0);

        final StringBuilder sb = new StringBuilder();
        if (!"".equals(packageName)) {
            sb.append("package ").append(packageName).append(";");
        }
        sb.append("public "); //TODO modifier
        sb.append("class ").append(getSimpleClassName());
        sb.append("{");
        sb.append(body);
        sb.append("}");

        return sb.toString();

    }


    public String getFileName() {
        return getSimpleClassName()+".java";
    }

    public ClassName getClassName() {
        return ClassName.of(name);
    }

    public String getPackageName() {
        return getClassName().getPackageName();
    }

    public String getSimpleClassName() {
        return getClassName().getSimpleClassName();
    }
}
