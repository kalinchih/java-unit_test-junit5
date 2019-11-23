package k0.junit5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HelloJunit_Test {
    private HelloJunit helloJunit = null;

    @BeforeEach
    void setUp() {
        this.helloJunit = new HelloJunit();
    }

    @AfterEach
    void tearDown() {
        this.helloJunit = null;
    }

    @Test
    void greeting_return_greeting_with_name() {
        String name = "Junit5";
        Assertions.assertEquals(String.format("Hello %s", name), helloJunit.greeting(name));
    }

    @Test
    void greeting_throws_exception_for_null_name() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    helloJunit.greeting(null);
                });
    }

    @Test
    void greeting_throws_exception_for_empty_name() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    helloJunit.greeting("");
                });
    }

    @Test
    void greeting_throws_exception_for_space_name() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    helloJunit.greeting(" ");
                });
    }

    @Test
    void greeting_throws_exception_for_tab_name() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    helloJunit.greeting("\t");
                });
    }
}
