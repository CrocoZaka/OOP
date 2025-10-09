package ru.nsu.ekovalenko4.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ParserTest {
    @Test
    void testParseNumber() {
        Expression e = Parser.parse("42");
        assertEquals("42.0", e.toString());
    }

    @Test
    void testParseNegativeNumber() {
        Expression e = Parser.parse("-7");
        assertEquals("-7.0", e.toString());
    }

    @Test
    void testParseVariable() {
        Expression e = Parser.parse("x");
        assertEquals("x", e.toString());
    }

    @Test
    void testParseAdd() {
        Expression e = Parser.parse("(3+x)");
        assertEquals("(3.0+x)", e.toString());
    }

    @Test
    void testParseSub() {
        Expression e = Parser.parse("(x-5)");
        assertEquals("(x-5.0)", e.toString());
    }

    @Test
    void testParseMul() {
        Expression e = Parser.parse("(2*y)");
        assertEquals("(2.0*y)", e.toString());
    }

    @Test
    void testParseDiv() {
        Expression e = Parser.parse("(x/2)");
        assertEquals("(x/2.0)", e.toString());
    }

    @Test
    void testParseExpression() {
        Expression e = Parser.parse("(3+((2*x)/5))");
        assertEquals("(3.0+((2.0*x)/5.0))", e.toString());
    }

    @Test
    void testParseInvalidExpression() {
        assertThrows(ParseException.class, () -> Parser.parse("3+2"));
        assertThrows(ParseException.class, () -> Parser.parse("(x*)"));
        assertThrows(ParseException.class, () -> Parser.parse("(3^2)"));
    }
}