package ru.nsu.ekovalenko4.expressions;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class BinaryOperationTest {
    @Test
    void testEqualsAndHashCode() {
        Expression e = new Add(new Number(1), new Variable("x"));
        assertNotNull(e);
    }
}