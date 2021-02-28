package net.masil.testing.compile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JavacTest {
// java source file  들
    // option
    // 주요 옵션들이 있지 .

    // 이 테스트를 위한 라이브러리에 관심을 가는 이유
    // 무엇을 테스트 하려고 하는 것인가.
    // 시작은 어노테이션 프로세스


    /** TODO
     *  [ ] 헬로우월드 컴파일
     *  [ ] 패키지 명 넣기
     *  [ ] 소스파일이 여러개 일때
     *  [ ] 옵션 : - d
     *  [ ] 옵션 : - processor
     *  [ ] 옵션: -cp
     */

    @Test
    void compile_hello_world() {

        final String helloWorld = "public class HelloWorld {  }";
        Compilation actual = Javac.init()
                .withSourceFile("HelloWorld", helloWorld)
                .compile();

        assertTrue(actual.hasClass(ClassName.of("HelloWorld")));

        final String illegal_helloWorld = "public classxxxs IllegalHelloWorld {  }";
        Compilation actual2 = Javac.init()
                .withSourceFile("IllegalHelloWorld", illegal_helloWorld)
                .compile();

        assertFalse(actual2.hasClass(ClassName.of("IllegalHelloWorld")));
    }
}