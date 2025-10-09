package ru.nsu.ekovalenko4.expressions;

import java.util.Map;

class Mul extends BinaryOperation {
    public Mul(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "*" + right.toString() + ")";
    }

    @Override
    public Expression derivative(String var) {
        return new Add(
                new Mul(left.derivative(var), right),
                new Mul(left, right.derivative(var))
        );
    }

    @Override
    public double eval(Map<String, Double> vars) {
        return left.eval(vars) * right.eval(vars);
    }
}