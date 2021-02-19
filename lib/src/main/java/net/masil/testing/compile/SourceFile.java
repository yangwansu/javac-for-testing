package net.masil.testing.compile;

public class SourceFile {

    private final ClassName name;
    private String body;

    public static SourceFile withName(String name) {
        return new SourceFile(name);
    }

    private SourceFile(String name) {
        this.name = ClassName.of(name);
    }

    public SourceFile withBody(String body) {
        this.body = body;
        return this;
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
