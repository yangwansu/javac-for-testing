package net.masil.testing.compile;

import net.masil.testing.compile.options.BuildDir;
import net.masil.testing.compile.options.Deprecation;
import net.masil.testing.compile.options.SourceCompatibility;
import net.masil.testing.compile.options.TargetCompatibility;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.masil.testing.compile.options.BuildDir.DEFAULT_BUILD_DIR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

class OptionListBuilderTest {

    /***
     *
     *   -g                         Generate all debugging info
     *   -g:none                    Generate no debugging info
     *   -g:{lines,vars,source}     Generate only some debugging info
     *   -nowarn                    Generate no warnings
     *   -verbose                   Output messages about what the compiler is doing
     *   -deprecation               Output source locations where deprecated APIs are used
     *   -classpath <path>          Specify where to find user class files and annotation processors
     *   -cp <path>                 Specify where to find user class files and annotation processors
     *   -sourcepath <path>         Specify where to find input source files
     *   -bootclasspath <path>      Override location of bootstrap class files
     *   -extdirs <dirs>            Override location of installed extensions
     *   -endorseddirs <dirs>       Override location of endorsed standards path
     *   -proc:{none,only}          Control whether annotation processing and/or compilation is done.
     *   -processor <class1>[,<class2>,<class3>...] Names of the annotation processors to run; bypasses default discovery process
     *   -processorpath <path>      Specify where to find annotation processors
     *   -parameters                Generate metadata for reflection on method parameters
     *   -d <directory>             Specify where to place generated class files
     *   -s <directory>             Specify where to place generated source files
     *   -h <directory>             Specify where to place generated native header files
     *   -implicit:{none,class}     Specify whether or not to generate class files for implicitly referenced files
     *   -encoding <encoding>       Specify character encoding used by source files
     *   -source <release>          Provide source compatibility with specified release
     *   -target <release>          Generate class files for specific VM version
     *   -profile <profile>         Check that API used is available in the specified profile
     *   -version                   Version information
     *   -help                      Print a synopsis of standard options
     *   -Akey[=value]              Options to pass to annotation processors
     *   -X                         Print a synopsis of nonstandard options
     *   -J<flag>                   Pass <flag> directly to the runtime system
     *   -Werror                    Terminate compilation if warnings occur
     *   @<filename> Read options and filenames from file
     *
     */

    @Test
    void buildDir() {

        OptionList op = OptionListBuilder.builder().build();

        assertEquals(DEFAULT_BUILD_DIR, op.buildDir());


    }
    /**
     * TODO [ ] 중복되면 맨 뒤가 우선순위가 높다
     * TODO [ ] 값 문자열 보다 명시적인 값이
     * TODO [ ] 값 문자열 보다 명시적인 값이
     * TODO [ ] desc ?
     * TODO [ ] 빌더 => 임뮤터블 뮤터블
     *
     */

    @Test
    void builder() {
        String buildDir = "foo/build/test/classes";
        OptionList ops = OptionListBuilder.builder()
                .buildDir(buildDir)
                .source("1.8")
                .target("1.8")
                .deprecation()
                .build();

        assertEquals(4, ops.size());

        assertLinesMatch(toList(
                BuildDir.OPTION_NAME, buildDir,
                SourceCompatibility.OPTION_NAME, "1.8",
                TargetCompatibility.OPTION_NAME, "1.8",
                Deprecation.OPTION_NAME
        ), ops.toList());

        assertEquals(buildDir, ops.buildDir());

    }


    private List<String> toList(String... str) {
        return Collections.unmodifiableList(Arrays.asList(str));
    }

}