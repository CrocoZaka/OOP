package ru.nsu.ekovalenko4.expressions;

import java.util.Map;

/**
 * Represents a constant integer number in an expression.
 */
class Number extends Expression {
    private final double value;

    public Number(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Expression derivative(String var) {
        return new Number(0);
    }

    @Override
    public double eval(Map<String, Double> values) {
        return value;
    }
}
