package net.masil.testing.compile;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Objects;

public class Compilation {

    private final List<Option> options;

    public Compilation(List<Option> options) {
        this.options = options;
    }

    public boolean hasClass(String className) {
        return hasClass(ClassName.of(className));
    }

    public boolean hasClass(ClassName className) {
        try {
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { new File(getBuildDir()).toURI().toURL() });
             Class.forName(className.toString(), true, classLoader);
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e);
            return false;
        } catch (MalformedURLException e) {
            System.err.println("base url not found: " + e);
            return false;
        }
        return true;
    }

    private String getBuildDir() {
        return options.stream().filter(o-> Objects.equals(o.getName(), Javac.BUILD_DIRECTORY)).findFirst().get().getValue();
    }

}
