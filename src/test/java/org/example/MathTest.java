package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MathTest {
    Math math;

    @BeforeEach
    void setUp() {
        math = new Math();
    }

    @AfterEach
    void tearDown() {
        math = null;
    }

    @Test
    @Order(1)
    @DisplayName(value = "Test with null arguments for sum method")
    void sumWithNull() {
        assertThrows(IllegalArgumentException.class, () -> math.sum(null));
    }

    @Test
    @Order(2)
    @DisplayName(value = "Test with arguments for sum method")
    void sum() {
        assertEquals(0, math.sum());
        assertEquals(5, math.sum(2, 3));
        assertEquals(9, math.sum(2, 3, 4));
        assertEquals(14, math.sum(2, 3, 4, 5));
    }

    @ParameterizedTest(name = "{displayName} => {index} : {arguments}")
    @Order(3)
    @DisplayName(value = "Test for sub method")
    @CsvSource(value = {
            "a,b,expected",
            "3,1,2",
            "5,2,3"
    }, useHeadersInDisplayName = true)
    void sub(int a, int b, int expected) {
        assertEquals(expected, math.sub(a, b));
    }

    @ParameterizedTest(name = "{displayName} => {index} : {arguments}")
    @Order(4)
    @DisplayName(value = "Test for add method")
    @CsvSource(value = {
            "a,b,expected",
            "3,1,4",
            "5,2,7"
    }, useHeadersInDisplayName = true)
    void add(int a, int b, int expected) {
        assertEquals(expected, math.add(a, b));
    }

    @ParameterizedTest(name = "{displayName} => {index} : {arguments}")
    @Order(5)
    @DisplayName(value = "Test for div method")
    @CsvSource(value = {
            "a,b,expected",
            "4,2,2",
            "15,3,5"
    }, useHeadersInDisplayName = true)
    void div(int a, int b, int expected) {
        assertEquals(expected, math.div(a, b));
    }

    @Test
    @Order(6)
    @DisplayName(value = "Test with zero divider for div method")
    void divWithZero() {
        assertThrows(IllegalArgumentException.class, () -> math.div(6, 0));
    }

    @ParameterizedTest(name = "{displayName} => {index} : {arguments}")
    @Order(7)
    @DisplayName(value = "Test for mul method")
    @CsvSource(value = {
            "a,b,expected",
            "3,1,3",
            "5,2,10"
    }, useHeadersInDisplayName = true)
    void mul(int a, int b, int expected) {
        assertEquals(expected, math.mul(a, b));
    }

    @ParameterizedTest(name = "{displayName} => {index} : {arguments}")
    @Order(8)
    @DisplayName(value = "Test for pow method")
    @CsvFileSource(resources = "/source.csv", useHeadersInDisplayName = true)
    void pow(int number, int power, int expected) {
        assertEquals(expected, math.pow(number, power));
    }
}