package k0.junit5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** @Before, @After, @Test, @Test(expected=Exception.class) */
public class HelloJunit_Test {

    private HelloJunit helloJunit = null;

    @BeforeEach
    public void before() {
        this.helloJunit = new HelloJunit();
    }

    @AfterEach
    public void after() {
        this.helloJunit = null;
    }

    @Test
    public void greeting_return_greeting_with_name() {
        String name = "Junit4";
        Assertions.assertEquals(String.format("Hello %s", name), helloJunit.greeting(name));
    }

    @Test
    public void greeting_throws_exception_for_null_name() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    helloJunit.greeting(null);
                });
    }

    @Test
    public void greeting_throws_exception_for_empty_name() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    helloJunit.greeting("");
                });
    }

    @Test
    public void greeting_throws_exception_for_space_name() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    helloJunit.greeting(" ");
                });
    }

    @Test
    public void greeting_throws_exception_for_tab_name() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    helloJunit.greeting("\t");
                });
    }
}
