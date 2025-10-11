package ru.nsu.ekovalenko4.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AddTest {
    @Test
    void testToString() {
        Expression e = new Add(new Number(3), new Variable("x"));
        assertEquals("(3.0+x)", e.toString());
    }

    @Test
    void testEval() {
        Expression e = new Add(new Number(3), new Variable("x"));
        assertEquals(13, e.eval("x=10"));
    }

    @Test
    void testDerivative() {
        Expression e = new Add(new Number(3), new Variable("x"));
        Expression der = e.derivative("x");
        assertEquals("(0.0+1.0)", der.toString());
    }
}