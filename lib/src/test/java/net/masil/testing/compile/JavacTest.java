package net.masil.testing.compile;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JavacTest {

    // java source file  들
    // option
    // 주요 옵션들이 있지 .

    // 이 테스트를 위한 라이브러리에 관심을 가는 이유
    // 무엇을 테스트 하려고 하는 것인가.
    // 시작은 어노테이션 프로세스

    @TempDir
    File tempDir;


    /**
     * TODO
     * [X] 헬로우월드 컴파일
     * [X] 패키지 명 넣기
     * [X] 소스파일이 여러개 일때
     * [X] 옵션 : - d build/classes --cp
     * [ ] 옵션 : - processor
     * [ ] 옵션: -cp
     * [X] source file 이 너무 과해 파일이름과 내용만 있으면 될것같음
     * [ ] 메서드 실행
     * [X] 옵션: -d 에 대한 죄악 제거하기
     * [X] -d 옵션을 사용하지 않으면 기본 빌드 디렉토리가 설정된다.
     */

    SourceFile helloWorld = SourceFile
            .withName("org.masil.testing.compile.HelloWorld")
            .withBody("public class HelloWorld { }");

    SourceFile foo = SourceFile
            .withName("org.masil.testing.compile.Foo")
            .withBody("public class Foo { }");

    SourceFile illegalHelloWorld = SourceFile.withName("org.masil.testing.compile.HelloWorld2")
            .withBody("package abc.edf;" +
                    "class HelloWorld2{" +
                    "" +
                    "}");

    @Test
    void compile_fail() {
        Compilation compilation = Javac.init()
                .with(illegalHelloWorld)
                .options(OptionListBuilder.builder().buildDir(tempDir.getAbsolutePath()))
                .compile();

        assertFalse(compilation.hasClass(illegalHelloWorld.getClassName()));
    }

    @Test
    void compile_hello_world() {
        Compilation compilation = Javac.init()
                .with(helloWorld, foo)
                .options(OptionListBuilder.builder().buildDir(tempDir.getAbsolutePath()))
                .compile();

        assertTrue(compilation.hasClass(helloWorld.getClassName()));
        assertTrue(compilation.hasClass(foo.getClassName()));
        assertFalse(compilation.hasClass(ClassName.of("org.masil.testing.compile.Bar")));
    }

    @Test
    void compile_hello_world_without_build_dir() {
        Compilation compilation = Javac.init()
                .with(helloWorld, foo)
                .compile();

        assertTrue(compilation.hasClass(helloWorld.getClassName()));
        assertTrue(compilation.hasClass(foo.getClassName()));
        assertFalse(compilation.hasClass(ClassName.of("org.masil.testing.compile.Bar")));
    }


}