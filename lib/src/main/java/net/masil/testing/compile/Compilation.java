package net.masil.testing.compile;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Compilation {

    private final OptionList optionList;

    public Compilation(OptionList optionList) {
        this.optionList = optionList;
    }

    public boolean hasClass(String className) {
        return hasClass(ClassName.of(className));
    }

    public boolean hasClass(ClassName className) {
        try {
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { new File(optionList.buildDir()).toURI().toURL() });
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


}
