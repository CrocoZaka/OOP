package ru.nsu.ekovalenko4.expressions;

import java.util.Map;

/**
 * Represents a variable in an expression.
 */
class Variable extends Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Expression derivative(String var) {
        if (name.equals(var)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }

    @Override
    public double eval(Map<String, Double> vars) {
        if (!vars.containsKey(name)) {
            throw new ExpressionException("Variable " + name + " is not defined properly");
        }
        return vars.get(name);
    }
}
