package ru.nsu.ekovalenko4.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class VariableTest {

    @Test
    void testToString() {
        Expression x = new Variable("x");
        assertEquals("x", x.toString());
    }

    @Test
    void testEval() {
        Expression x = new Variable("x");
        assertEquals(10.0, x.eval("x=10; y=5"));
    }

    @Test
    void testEvalException() {
        Expression x = new Variable("x");
        assertThrows(CalculatorException.class, () -> x.eval("y=5; z=8; "));
    }

    @Test
    void testDerivativeSameVariable() {
        Expression x = new Variable("x");
        Expression der = x.derivative("x");
        assertEquals("1.0", der.toString());
    }

    @Test
    void testDerivativeDifferentVariable() {
        Expression x = new Variable("x");
        Expression der = x.derivative("y");
        assertEquals("0.0", der.toString());
    }

}