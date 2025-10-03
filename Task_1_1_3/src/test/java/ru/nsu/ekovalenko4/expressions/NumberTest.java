package ru.nsu.ekovalenko4.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NumberTest {

    @Test
    void testToString() {
        Expression num = new Number(42);
        assertEquals("42.0", num.toString());
    }

    @Test
    void testEval() {
        Expression num = new Number(7);
        assertEquals(7, num.eval("x=10; y=20"));
    }

    @Test
    void testDerivative() {
        Expression num = new Number(99);
        Expression der = num.derivative("x");
        assertEquals("0", der.toString());
        assertEquals(0.0, der.eval(""));
    }

}