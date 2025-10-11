package ru.nsu.ekovalenko4.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DivTest {
    @Test
    void testToString() {
        Expression e = new Div(new Number(3), new Variable("x"));
        assertEquals("(3.0/x)", e.toString());
    }

    @Test
    void testEval() {
        Expression e = new Div(new Number(3), new Variable("x"));
        assertEquals(0.6, e.eval("x=5"));
    }

    @Test
    void testDerivative() {
        Expression e = new Div(new Number(3), new Variable("x"));
        Expression der = e.derivative("x");
        assertEquals("(((0.0*x)-(3.0*1.0))/(x*x))", der.toString());
    }

    @Test
    void testDivisionByZero() {
        Expression e = new Div(new Number(3), new Variable("x"));
        assertThrows(CalculatorException.class, () -> e.eval("x=0"));
    }
}