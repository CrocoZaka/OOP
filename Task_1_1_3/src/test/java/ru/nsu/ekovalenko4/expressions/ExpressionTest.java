package ru.nsu.ekovalenko4.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ExpressionTest {

    @Test
    void testDerivative() {
        Expression e = new Mul(new Number(2), new Variable("y")); // (2*y)
        assertEquals("(2.0*y)", e.toString());
    }

    @Test
    void testEval() {
        Expression e = new Div(new Variable("x"), new Number(5)); // (x/5)
        assertEquals(2.4, e.eval("x=12"));
    }

}