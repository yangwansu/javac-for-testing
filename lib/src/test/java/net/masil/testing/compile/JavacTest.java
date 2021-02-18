package net.masil.testing.compile;

import org.junit.jupiter.api.Test;

import static java.lang.reflect.Modifier.PUBLIC;
import static net.masil.testing.compile.Javac.BUILD_DIRECTORY;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JavacTest {

    // java source file  들
    // option
    // 주요 옵션들이 있지 .

    // 이 테스트를 위한 라이브러리에 관심을 가는 이유
    // 무엇을 테스트 하려고 하는 것인가.
    // 시작은 어노테이션 프로세스


    /**
     * TODO
     * [X] 헬로우월드 컴파일
     * [X] 패키지 명 넣기
     * [ ] 소스파일이 여러개 일때
     * [X] 옵션 : - d build/classes --cp
     * [ ] 옵션 : - processor
     * [ ] 옵션: -cp
     */

    @Test
    void compile_hello_world() {

        SourceFile helloWorld = SourceFile.withName("org.masil.testing.compile.HelloWorld")
                .addModifier(PUBLIC)
                .withEmptyBody();

        assertTrue(Javac.init()
                .with(helloWorld)
                .options(BUILD_DIRECTORY, "build/test1")
                .compile().hasClass(helloWorld.getClassName()));

        SourceFile illegalHelloWorld = SourceFile.withName("HelloWorld")
                .addModifier(PUBLIC)
                .withBody("xxxxxxxxxxx");

        assertFalse(Javac.init()
                .with(illegalHelloWorld)
                .options(BUILD_DIRECTORY, "build/test2")
                .compile().hasClass(illegalHelloWorld.getClassName()));
    }
}