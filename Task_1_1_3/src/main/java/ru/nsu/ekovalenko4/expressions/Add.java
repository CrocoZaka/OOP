package ru.nsu.ekovalenko4.expressions;

import java.util.Map;

class Add extends BinaryOperation {
    public Add(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "+" + right.toString() + ")";
    }

    @Override
    public Expression derivative(String var) {
        return new Add(left.derivative(var), right.derivative(var));
    }

    @Override
    public double eval(Map<String, Double> vars) {
        return left.eval(vars) + right.eval(vars);
    }
}