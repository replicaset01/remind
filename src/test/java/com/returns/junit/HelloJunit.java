package com.returns.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HelloJunit {
    @DisplayName("Hello Junit Test")
    @Test
    public void assertionTest() {
        String expected = "Hello, Junit";
        String actual = "Hello, JUnit";

        Assertions.assertEquals(expected,actual);
    }
}
