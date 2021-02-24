package net.masil.testing.compile;

import org.junit.jupiter.api.Test;

import static net.masil.testing.compile.Options.DEFAULT_BUILD_DIR;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionBuilderTest {

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
        Options op1 = Options.init();

        String buildDir = "foo/build/test/classes";
        Options op2 = op1.set(Options.BUILD_DIRECTORY, buildDir);

        assertEquals(DEFAULT_BUILD_DIR, op1.buildDir());
        assertEquals(buildDir, op2.buildDir());
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
        Options op = OptionBuilder.builder()
                .buildDir(buildDir)
                .source("1.8")
                .target("1.8")
                .deprecation()
                .build();

        assertEquals(buildDir, op.buildDir());
        assertEquals("1.8", op.source());
        assertEquals("1.8", op.target());
        assertEquals(true, op.deprecation());

    }
}