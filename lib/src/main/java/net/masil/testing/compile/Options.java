package net.masil.testing.compile;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Options {

    public static final String BUILD_DIRECTORY = "-d";
    public static final String DEFAULT_BUILD_DIR = "build/test/classes";

    private final List<Option> options;

    private Options() {
        this(new ArrayList<>());
    }


    Options(List<Option> options) {
        this.options = Collections.unmodifiableList(options);
    }

    public static Options init() {
        return new Options();
    }


    @Override
    public String toString() {
        return buildOptionStr().stream().reduce(new StringBuilder(), (sb, str) -> sb.append(str).append(" "), (sb1, sb2) -> null).toString().trim();
    }

    String buildDir() {
        return options.stream().filter(o -> Objects.equals(o.getName(), BUILD_DIRECTORY)).map(Option::getValue).findFirst().orElse(DEFAULT_BUILD_DIR);
    }

    List<String> buildOptionStr() {
        List<String> optionsStr = new ArrayList<>();
        //processorOption(optionsStr);

        buildDir(optionsStr);

        return optionsStr;
    }

    private void buildDir(List<String> optionsStr) {
        if (options.stream().noneMatch(o -> BUILD_DIRECTORY.equals(o.getName()))) {
            optionsStr.add(BUILD_DIRECTORY);
            File file1 = new File(DEFAULT_BUILD_DIR);
            optionsStr.add(file1.getAbsolutePath());
        } else {
            File file1 = new File(buildDir());
            optionsStr.add(BUILD_DIRECTORY);
            optionsStr.add(file1.getAbsolutePath());
        }

    }

    public Options set(String optionName, String optionValue) {
        ArrayList<Option> list = new ArrayList<>();
        list.add(new Option(optionName, optionValue));
        return new Options(list);
    }

    public String source() {
        return options.stream().filter(o -> Objects.equals(o.getName(), "-source")).map(Option::getValue).findFirst().orElse(DEFAULT_BUILD_DIR);
    }

    public String target() {
        return options.stream().filter(o -> Objects.equals(o.getName(), "-target")).map(Option::getValue).findFirst().orElse(DEFAULT_BUILD_DIR);
    }

    public boolean deprecation() {
        return options.stream().anyMatch(o -> Objects.equals(o.getName(), "-deprecation"));
    }

    /**
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
    */

}
