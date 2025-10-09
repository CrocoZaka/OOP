package ru.nsu.ekovalenko4.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ExpressionTest {

    @Test
    void testDerivative() {
        Expression e = new Mul(new Number(2), new Variable("y"));
        assertEquals("(2.0*y)", e.toString());
    }

    @Test
    void testSimpleEval() {
        Expression e1 = new Div(new Variable("x"), new Number(5));
        Expression e2 = new Sub(new Number(50), new Number(5));
        Expression e3 = new Mul(new Variable("y"), new Number(10));

        assertEquals(2.4, e1.eval("x=12"));
        assertEquals(45.0, e2.eval(""));
        assertEquals(5, e3.eval("y=0.5"));
    }

    @Test
    void testMultipleVarsEval() {
        Expression e = Parser.parse("(y+(2*x))");
        assertEquals(23.0, e.eval("x=10; y=3; z=5; a = 1 = 2; "));
    }

    @Test
    void testMultiLetterVariable() {
        Expression e = new Add(new Variable("alpha"), new Variable("beta"));
        assertEquals(15, e.eval("alpha=7; beta=8"));
    }

}